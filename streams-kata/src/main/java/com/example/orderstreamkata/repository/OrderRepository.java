package com.example.orderstreamkata.repository;

import com.example.orderstreamkata.model.Order;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public interface OrderRepository  {

    public List<Order> findAll();

    public void add(Order order);

    public Optional<Order> getById(Long id);
}
