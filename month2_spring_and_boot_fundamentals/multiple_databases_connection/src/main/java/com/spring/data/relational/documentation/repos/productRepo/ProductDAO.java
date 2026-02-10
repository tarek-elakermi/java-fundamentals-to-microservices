package com.spring.data.relational.documentation.repos.productRepo;

import com.spring.data.relational.documentation.entities.productsEntity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductDAO extends CrudRepository<Product,Long> {
}
