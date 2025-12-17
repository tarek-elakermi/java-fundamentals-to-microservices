package com.tarek.cliapp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceConfig {


    private static HikariDataSource dataSource;


    public static DataSource getDataSource(){
        if(dataSource == null) {
            HikariConfig config = new HikariConfig();

            // JDBC URL format: jdbc:postgresql://host:port/database
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/cliappdb");
            config.setUsername("postgres");
            config.setPassword("tarek");
            config.setMaximumPoolSize(10); // max concurrent connections


            dataSource = new HikariDataSource(config);

        }


        return dataSource;
    }
}
