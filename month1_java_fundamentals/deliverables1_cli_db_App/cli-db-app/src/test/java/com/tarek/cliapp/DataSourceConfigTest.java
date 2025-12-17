package com.tarek.cliapp;

import com.tarek.cliapp.config.DataSourceConfig;
import com.tarek.cliapp.config.FlywayConfig;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DataSourceConfigTest {


    @Test
    void testConnection() {

        try(Connection conn = DataSourceConfig.getDataSource().getConnection()){
            // check that the connection is not null
            assertNotNull(conn, "Connection should not be null");
            System.out.println("Connection to PostgreSQL successful!");
        }catch (SQLException e){
            // fail the test if exception occurs
            throw new RuntimeException("Connection failed", e);
        }
    }

    @Test
    void testMigrations(){
        // raw migration
        assertDoesNotThrow(() -> {
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/cliappdb",
                    "postgres",
                    "tarek")) {
                System.out.println("Raw JDBC connection successful!");
            }
        });
    }

    @Test
    void testFlywayDriverAvailable() {
        assertDoesNotThrow(() -> Class.forName("org.postgresql.Driver"),
                "PostgreSQL driver should be available on classpath");
    }

    @Test
    void testFlywayConfigMigrations() {
        assertDoesNotThrow(FlywayConfig::migrate);
    }




}
