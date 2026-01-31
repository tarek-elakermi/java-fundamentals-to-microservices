package com.spring.data.relational.documentation;


import com.spring.data.relational.documentation.entities.TestEntity;
import com.spring.data.relational.documentation.repos.TestEntityRepository;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.mapping.event.BeforeSaveEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication
//@EnableJdbcRepositories(basePackages = "com.spring.data.relational.documentation.repos")
public class DocumentationApplication{

    private static final Log log = LogFactory.getLog(DocumentationApplication.class);


//    @Autowired
//    TestEntityRepository repo;


	public static void main(String[] args) {
		SpringApplication.run(DocumentationApplication.class, args);

	}


    /*** with Repository JDBC work***/
        //    @Override
//    public void run(String... args) throws Exception {
//
//        TestEntity e1 = new TestEntity();
//        e1.setEntityName("first_entity");
//
//
//        TestEntity e2 = new TestEntity();
//        e2.setEntityName("Tarek");
//
//        repo.save(e1);
//        repo.save(e2);
//
//        repo.findAll().forEach(e ->
//                System.out.println(e.getId() + " | " + e.getEntityName())
//        );
//
//    }
    /*********/


    /*** Row JDBC work ***/
        //    @Override
        //    public void run(String... args) throws Exception {
        //        System.out.println("***** Getting Datasource connection *****");
        //        System.out.println("data source connection:" + dataSource.getConnection());
        //        System.out.println("template jdbc connection" + namedParameterJdbcOperations);
        //
        ////        String createSeq = """
        ////        CREATE SEQUENCE IF NOT EXISTS my_seq START WITH 1 INCREMENT BY 1
        ////            """;
        //
        //        String createTable = """
        //        CREATE TABLE IF NOT EXISTS test_entity (
        //            id BIGINT PRIMARY KEY,
        //            entity_name VARCHAR(255)
        //        )
        //    """;
        //
        //        //namedParameterJdbcOperations.getJdbcOperations().execute(createSeq);
        //        namedParameterJdbcOperations.getJdbcOperations().execute(createTable);
        //
        //        System.out.println("***** Creating table *****");
        //        System.out.println("Table Created with name test_entity");
        //
        //
        //        System.out.println("***** Insertion *****");
        //
        //        String insertSql = """
        //                INSERT INTO test_entity (id, entity_name)
        //                VALUES (1,:name)
        //                """;
        //        MapSqlParameterSource params = new MapSqlParameterSource()
        //                .addValue("name","first entity");
        //
        //        namedParameterJdbcOperations.update(insertSql, params);
        //
        //        String insertSql1 = """
        //                INSERT INTO test_entity (id, entity_name)
        //                VALUES (2, :name)
        //                """;
        //        MapSqlParameterSource params1 = new MapSqlParameterSource()
        //                .addValue("name","Tarek");
        //
        //        namedParameterJdbcOperations.update(insertSql1, params1);
        //
        //        String insertSql2 = """
        //                INSERT INTO test_entity (id, entity_name)
        //                VALUES (3, :name)
        //                """;
        //        MapSqlParameterSource params2 = new MapSqlParameterSource()
        //                .addValue("name","Jamila");
        //
        //        namedParameterJdbcOperations.update(insertSql2, params2);
        //
        //        String selectSQL = """
        //                SELECT id, entity_name FROM test_entity
        //                """;
        //
        //        List<TestEntity> result =
        //                namedParameterJdbcOperations.query(
        //                        selectSQL,
        //                        (rs, rowNum) -> {
        //                            TestEntity t = new TestEntity();
        //                            t.setId(rs.getLong("id"));
        //                            t.setEntityName(rs.getString("entity_name"));
        //                            return t;
        //                        }
        //                );
        //
        //        System.out.println("----- DATA IN test_entity TABLE -----");
        //        result.forEach(
        //                e -> System.out.println(e.getId() + " | " + e.getEntityName())
        //        );
        //
        //        String describeSql = "DESCRIBE test_entity";
        //
        //        namedParameterJdbcOperations.query(describeSql, rs -> {
        //            System.out.println(
        //                    rs.getString("Field") + " | " +
        //                            rs.getString("Type") + " | " +
        //                            rs.getString("Null") + " | " +
        //                            rs.getString("Key") + " | " +
        //                            rs.getString("Default") + " | " +
        //                            rs.getString("Extra")
        //            );
        //        });
        //
        //
        //
        //
        //
        //
        //
        //
        //    }
    /*********/
}
