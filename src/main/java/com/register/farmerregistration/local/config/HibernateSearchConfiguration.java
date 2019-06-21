package com.register.farmerregistration.local.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class HibernateSearchConfiguration {

    private final EntityManager bentityManager;


    @Autowired
    public HibernateSearchConfiguration(@Qualifier("entityManagerFactory") EntityManager entityManager) {
        this.bentityManager = entityManager;
    }


    @Bean
    HibernateSearchService hibernateSearchService() {
        HibernateSearchService hibernateSearchService = new HibernateSearchService(bentityManager.getEntityManagerFactory());
        hibernateSearchService.initializeHibernateSearch();
        return hibernateSearchService;
    }
}
