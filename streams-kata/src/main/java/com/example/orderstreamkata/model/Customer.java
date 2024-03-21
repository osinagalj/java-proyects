package com.example.orderstreamkata.model;

import static java.util.Objects.requireNonNull;

public record Customer (
    Long id,
    String name) {
    public Customer(Long id, String name) {
        this.id = requireNonNull(id,"Customer Id is mandatory");
        this.name = requireNonNull(name, "Customer Name must be completed");
    }
}
