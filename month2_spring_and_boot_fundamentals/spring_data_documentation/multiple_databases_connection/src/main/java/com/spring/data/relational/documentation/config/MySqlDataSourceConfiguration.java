package com.spring.data.relational.documentation.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;


@Configuration
public class MySqlDataSourceConfiguration {


    @ConfigurationProperties(
            prefix = "spring.datasource.mysql"
    )
    @Bean
    public DataSourceProperties mySqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource mySqlDatasource() {

        // first way to set up the data source bean

//        DriverManagerDataSource dataSource =
//                new DriverManagerDataSource();
//        dataSource.setUrl(mySqlDataSourceProperties().getUrl());
//        dataSource.setUsername(mySqlDataSourceProperties().getUsername());
//        dataSource.setPassword(mySqlDataSourceProperties().getPassword());
//        dataSource.setDriverClassName(Objects.requireNonNull(mySqlDataSourceProperties().getDriverClassName()));

        // second way dynamic way internally by spring (better choice)
        DataSource mySqlDataSource = mySqlDataSourceProperties().initializeDataSourceBuilder().build();

        return mySqlDataSource;
    }
}
