package com.spring.data.deliverable2_librarySystem.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DatabasesConfig {


    // Mysql database
    @Primary
    @Bean
    @ConfigurationProperties(
            prefix = "spring.datasource"
    )
    public DataSourceProperties mySqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource() {
        return mySqlDataSourceProperties().initializeDataSourceBuilder().build();

    }

    // H2 database
    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(
            prefix = "h2.datasource"
    )
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create()
                //.url("jdbc:h2:mem:reportdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE")
                .url("jdbc:h2:mem:reportdb;INIT=RUNSCRIPT FROM 'classpath:schema-h2.sql'")
                .driverClassName("org.h2.Driver")
                .username("sa")
                .password("")
                .build();
    }

    // JdbcTemplate for H2 database
    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(secondaryDataSource());
    }



}
