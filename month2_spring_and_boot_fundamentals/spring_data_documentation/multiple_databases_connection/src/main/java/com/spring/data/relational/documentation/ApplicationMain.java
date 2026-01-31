package com.spring.data.relational.documentation;


import com.spring.data.relational.documentation.entities.orderEntity.Order;
import com.spring.data.relational.documentation.entities.productsEntity.Product;
import com.spring.data.relational.documentation.repos.orderRepo.OrderDAO;
import com.spring.data.relational.documentation.repos.productRepo.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private OrderDAO orderDAO;

	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}


    @Override
    public void run(String... args) throws Exception {

        System.out.println("-------- starting inserting data -------");
        Product product = new Product(1L,"laptop",2000.00);
        productDAO.save(product); // mysql

        Order order = new Order(1L,"tarek elakermi", LocalDate.now());
        orderDAO.save(order); // postgresql
        System.out.println("-------- insertion Completed -------");

    }
}
