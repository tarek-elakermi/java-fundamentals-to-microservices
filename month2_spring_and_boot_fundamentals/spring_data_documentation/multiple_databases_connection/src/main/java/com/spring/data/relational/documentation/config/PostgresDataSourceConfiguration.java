package com.spring.data.relational.documentation.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgresDataSourceConfiguration {


    @ConfigurationProperties(
            prefix = "spring.datasource.pg"
    )
    @Bean
    public DataSourceProperties postgresDataSourceProperties() {
        return new DataSourceProperties();
    }


    //@Primary
    @Bean
    public DataSource postgresDataSource() {

        DataSource postgresDataSource = postgresDataSourceProperties().initializeDataSourceBuilder().build();
        return postgresDataSource;

    }
}
