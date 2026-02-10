//package com.spring.boot.jpa_docs.config;
//
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.TransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableTransactionManagement
//public class DBConfig {
//
//
//    // set Up a Connection
//    @Bean
//    DriverManagerDataSource dataSource() {
//        DriverManagerDataSource dataSource =
//                new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_boot_jpa");
//        dataSource.setUsername("root");
//        dataSource.setPassword("tarek");
//
//        return dataSource;
//    }
//
//    // create the bean of EntityManagerFactory
//    @Bean
//    LocalContainerEntityManagerFactoryBean entityManagerFactory(){
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
//                new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource());
//        entityManagerFactoryBean.setPackagesToScan("com.spring.boot.jpa_docs.entities");
//        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        entityManagerFactoryBean.setPersistenceUnitName("mysqldb");
//
//        return entityManagerFactoryBean;
//    }
//
//    @Bean
//    PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//        // whenever we are connecting to 1 database we use JpaTransactionManager
//        // else if we are connecting to multiple databases JtaTransactionManager is more suitable
//        JpaTransactionManager jpaTransactionManager =
//                new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(emf);
//        return jpaTransactionManager;
//    }
//
//}
