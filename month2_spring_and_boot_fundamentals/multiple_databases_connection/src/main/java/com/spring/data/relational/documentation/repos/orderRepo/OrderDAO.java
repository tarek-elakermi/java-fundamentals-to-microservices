package com.spring.data.relational.documentation.repos.orderRepo;

import com.spring.data.relational.documentation.entities.orderEntity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderDAO extends CrudRepository<Order, Long> {
}
