package com.example.orderstreamkata.service;

import com.example.orderstreamkata.model.Order;
import com.example.orderstreamkata.model.Product;
import com.example.orderstreamkata.repository.OrderRepository;
import com.example.orderstreamkata.repository.OrderService;
import com.example.orderstreamkata.repository.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final OrderRepository orderRepository;

    public ProductServiceImpl(OrderRepository repository) {
        orderRepository = repository;
    }

    /**
     * Finds products within a specific category and with a minimum price.
     *
     * @param category The category to search for.
     * @param minPrice The minimum price of the products.
     * @return A list of products within the specified category and meeting the minimum price requirement.
     */
    @Override
    public List<Product> findByCategoryAndPrice(String category, BigDecimal minPrice) {
        return orderRepository.findAll().stream()
                .flatMap(o -> o.products().stream())
                .filter(p -> (p.category().equals(category) && p.price().compareTo(minPrice) >= 0))
                .toList();
    }

    /**
     * Finds the cheapest product within a specific category.
     *
     * @param category The category to search for.
     * @return The cheapest product within the specified category.
     * @throws IllegalArgumentException if no product is found.
     */
    @Override
    public Product findCheapestByCategory(String category) {
        return orderRepository.findAll().stream()
                .flatMap(order -> order.products().stream())
                .filter(product -> product.category().equals(category))
                .min(Comparator.comparing(Product::price))
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }

    /**
     * Finds the most expensive product within a specific category.
     *
     * @param category The category to search for.
     * @return The most expensive product within the specified category.
     * @throws IllegalArgumentException if no product is found.
     */
    @Override
    public Product findExpensiveProductByCategory(String category) {
        return orderRepository.findAll().stream()
                .flatMap(order -> order.products().stream())
                .filter(product -> product.category().equals(category))
                .max(Comparator.comparing(Product::price))
                .orElseThrow(() -> new IllegalArgumentException("Not found"));
    }


}