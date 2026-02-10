package com.spring.data.relational.documentation.entities.productsEntity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "amazon_product")
public class AmazonProduct {

    @Id
    private Long id;
    private String amProductName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmProductName() {
        return amProductName;
    }

    public void setAmProductName(String amProductName) {
        this.amProductName = amProductName;
    }
}
