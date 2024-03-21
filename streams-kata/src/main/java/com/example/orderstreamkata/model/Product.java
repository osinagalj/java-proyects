package com.example.orderstreamkata.model;

import java.math.BigDecimal;

import static java.util.Objects.requireNonNull;

public record Product (
    Long id,
    String name,
    String category,
    BigDecimal price) {

    public Product(Long id, String name, String category, BigDecimal price) {
        this.id = requireNonNull(id, "Product Id is mandatory");
        this.name = requireNonNull(name, "Name is mandatory");
        this.category = requireNonNull(category, "Category is mandatory");
        this.price = requireNonNull(price, "Price is mandatory");
    }
}
