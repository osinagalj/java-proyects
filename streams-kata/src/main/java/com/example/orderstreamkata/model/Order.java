package com.example.orderstreamkata.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.*;

public record Order (
        Long id,
        OrderStatus status,
        LocalDateTime date,
        LocalDateTime deliveryDate,
        List<Product> products,
        String customer) {

    public Order {
        requireNonNull(id, "Id must be completed");
        requireNonNull(status, "Status must be completed");
        requireNonNull(date, "Date must be completed");
        requireNonNullElse(products, new ArrayList<>());
    }

    public Order(Long id, OrderStatus status, LocalDateTime date, List<Product> products, String customer) {
        this(id, status, date, null, products, customer); // Delegando al constructor principal del registro
    }

    public Order(Long id, OrderStatus status, LocalDateTime date, List<Product> products, String customer, LocalDateTime deliveryDate) {
        this(id, status, date, deliveryDate, products, customer);
    }

    public boolean containsProduct(String productName) {
        return products
                .stream()
                .anyMatch(p -> p.name().equals(productName));
    }

    public BigDecimal total() {
        return products.stream()
                .map(Product::price)
                .reduce(BigDecimal.ZERO, (b1, b2) -> b1.add(b2));
    }
}
