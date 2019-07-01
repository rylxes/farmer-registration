package com.register.farmerregistration.local.config;


import com.register.farmerregistration.local.entities.User;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.yuequan.jpa.soft.delete.repository.EnableJpaSoftDeleteRepositories;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableAsync
@Slf4j
@Configuration
//@EnableJpaSoftDeleteRepositories(
//        basePackages = {"com.register.farmerregistration.local.repository"}
//)
@EnableJpaRepositories(
        basePackages = {"com.register.farmerregistration.local.repository"}
)
@EnableTransactionManagement
@ConfigurationProperties(prefix = "spring.datasource")
public class HsqlConfig extends HikariConfig {


    private JpaProperties jpaProperties;

    @Autowired
    public void setJpaProperties(JpaProperties jpaProperties) {
        this.jpaProperties = jpaProperties;
    }

    private JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }


    @Primary
    @Bean(name = "hsqlDataSource")
    public DataSource dataSource() {
        this.setMaximumPoolSize(50);
        return new HikariDataSource(this);
    }


    @Primary
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setPackagesToScan(User.class.getPackage().getName());
        em.setJpaVendorAdapter(this.jpaVendorAdapter());
        em.setDataSource(dataSource());
        em.setJpaPropertyMap(hibernateProperties());
        em.afterPropertiesSet();

        return em;
    }

    @Primary
    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }


    public Map<String, Object> hibernateProperties() {

        Map<String, Object> props = new HashMap<String, Object>(jpaProperties.getProperties());

        //props.put("hibernate.connection.url", url);
        props.put("hibernate.connection.autoReconnect", "true");
        props.put("hibernate.current_session_context_class", "thread");
        //props.put("hibernate.format_sql", "false");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        //props.put("hibernate.connection.driver_class", "org.hsqldb.jdbc.JDBCDriver");
        props.put("hibernate.hbm2ddl.jdbc_metadata_extraction_strategy", "individually");
        return props;


    }
}
