package com.spring.data.jdbc_internals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
//        (exclude = {
//        JdbcTemplateAutoConfiguration.class,
//        DataSourceAutoConfiguration.class
//})
public class JdbcInternalsApplication implements CommandLineRunner {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(JdbcInternalsApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("insert into student values (1,'Tarek','Tunsia')");
        System.out.println("Data inserted to the table ");

        System.out.println("data source connection :" + dataSource.getConnection());
    }
}
