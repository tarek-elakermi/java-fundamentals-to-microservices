package com.tarek.cliapp.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

public class FlywayConfig {

    public static void migrate() {
        try {
            // Ensure driver is loaded
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL driver not found", e);
        }

        // HikariCP DataSource configuration
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/cliappdb");
        config.setUsername("postgres");
        config.setPassword("tarek");
        config.setDriverClassName("org.postgresql.Driver");

        HikariDataSource ds = new HikariDataSource(config);

        // Flyway migration
        Flyway flyway = Flyway.configure()
                .dataSource(ds) // pass DataSource directly
                .locations("classpath:db/migration") // migration scripts folder
                .load();

        flyway.migrate();

        System.out.println("Flyway migrations applied successfully!");
    }
}
