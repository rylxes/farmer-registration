package com.register.farmerregistration.local.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//@Component
//@NoRepositoryBean
public interface BaseRepository<T, S> extends JpaRepository<T, S>, JpaSpecificationExecutor<T> {
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE #{#entityName} SET isDeleted = '1' where id =:id")
    void softDelete(@Param("id") S id);

}
