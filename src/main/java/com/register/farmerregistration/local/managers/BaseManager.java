package com.register.farmerregistration.local.managers;

import com.register.farmerregistration.local.annotation.EntityAnnotation;
import com.register.farmerregistration.local.entities.AppModel;
import com.register.farmerregistration.local.repository.BaseRepository;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Service
public abstract class BaseManager<T, V> {

    private BaseRepository<T, V> repo;
    private EntityManager centityManager;
    private long returnedID;

    @Autowired
    public void setCentityManager(@Qualifier("entityManagerFactory") EntityManager centityManager) {
        this.centityManager = centityManager;
    }

    @Autowired
    public void setRepo(BaseRepository<T, V> repo) {
        this.repo = repo;
    }


    public T findById(V id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Entity not found"));
    }

    public T findFirst() {
        try {
            return repo.findAll().get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<T> findAll() {
        return repo.findAll();
    }

    public List<T> findAll(int pageNumber, int pageSize) {
        int page = pageNumber - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        return repo.findAll(pageable).getContent();
    }


    public boolean replace(T model) {
        return save(model);
    }

    public boolean update(T model) {
        return save(model);
    }


    @SuppressWarnings("unchecked")
    public <U extends AppModel> void update(U model, Button btnSave) {
        if (update((T) model)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Update Successful", ButtonType.OK, ButtonType.CANCEL);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(btnSave.getScene().getWindow());
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                Stage stage = (Stage) btnSave.getScene().getWindow();
                stage.close();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, model.getError());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(btnSave.getScene().getWindow());
            alert.showAndWait();
        }
    }

    public <U extends AppModel> void save(U model, Button btnSave) {
        insert(model, btnSave);
    }


    @SuppressWarnings("unchecked")
    public <U extends AppModel> void insert(U model, Button btnSave) {
        if (insert((T) model)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Insert Successful", ButtonType.OK, ButtonType.CANCEL);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(btnSave.getScene().getWindow());
            alert.showAndWait();
            if (alert.getResult() == ButtonType.OK) {
                Stage stage = (Stage) btnSave.getScene().getWindow();
                stage.close();
            }


        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, model.getError());
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(btnSave.getScene().getWindow());
            alert.showAndWait();
        }
    }

    public long findAllCount() {
        return repo.count();
    }

    public boolean insert(T model) {
        return save(model);
    }

    public void deleteModel(V id) {
        repo.deleteById(id);
    }

    public void delete(V id) throws SQLException {
        repo.deleteById(id);
    }

    private Class getGenericParameterType() {
        try {
            final Class aClass = this.getClass();
            EntityAnnotation ne = AnnotationUtils.findAnnotation(aClass, EntityAnnotation.class);
            return ne.entityClass();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public List<T> findByString(String name, int pageNumber, int pageSize, String... columns) {


        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(getGenericParameterType()).get();
        Query luceneQuery = qb.keyword().fuzzy()
                .withEditDistanceUpTo(1)
                .withPrefixLength(1)
                .onFields(columns)
                .matching(name).createQuery();

        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, getGenericParameterType());
        jpaQuery.setFirstResult(pageSize * (pageNumber - 1));
        jpaQuery.setMaxResults(pageSize);

        // execute search

        List<T> ResultList = null;
        try {
            ResultList = jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }

        return ResultList;
    }


    public boolean save(T model) {
        try {
            repo.save(model);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return false;
        }
    }

    public T saveModel(T model) {
        try {
            return repo.save(model);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error(ex.getMessage());
            return null;
        }
    }
}
