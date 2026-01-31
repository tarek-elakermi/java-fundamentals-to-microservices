package com.spring.data.relational.documentation.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@EnableTransactionManagement
@Configuration
@EnableJpaRepositories(
        basePackages = "com.spring.data.relational.documentation.repos.productRepo",
        entityManagerFactoryRef = "mySqlEntityManagerFactoryBean",
        transactionManagerRef = "mySqlTransactionManager"
)
public class MySql_EM_config {



    @Bean
    public LocalContainerEntityManagerFactoryBean mySqlEntityManagerFactoryBean(
            //@Qualifier("entityManagerFactoryBuilder")
            EntityManagerFactoryBuilder emfb,
            @Qualifier("mySqlDatasource") DataSource dataSource
    ) {
        return emfb
                .dataSource(dataSource)
                .packages("com.spring.data.relational.documentation.entities.productsEntity")
                .build();
    }

    @Bean
    public PlatformTransactionManager mySqlTransactionManager(
            @Qualifier("mySqlEntityManagerFactoryBean") LocalContainerEntityManagerFactoryBean emfb
    ) {
        return new JpaTransactionManager(emfb.getObject());

    }


}
