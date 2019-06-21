package com.register.farmerregistration.local.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Service
@Transactional
public class HibernateSearchService {

    private final EntityManager entityManager;

    @Autowired
    public HibernateSearchService(final EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @PostConstruct
    @Transactional
    public void initializeHibernateSearch() {

//        try {
//            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
//            fullTextEntityManager.createIndexer().startAndWait();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

}
