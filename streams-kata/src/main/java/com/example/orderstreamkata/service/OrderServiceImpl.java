package com.example.orderstreamkata.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import com.example.orderstreamkata.model.Order;
import com.example.orderstreamkata.model.OrderStatus;
import com.example.orderstreamkata.model.Product;
import com.example.orderstreamkata.repository.OrderRepository;
import com.example.orderstreamkata.repository.OrderService;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository repository) {
        orderRepository = repository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void add(Order order) {
        orderRepository.add(order);
    }

    public Optional<Order> getById(Long id) {
        return orderRepository.getById(id);
    }


    /**
     * Search for orders that contain a product, identifying this product by its name.
     * @param productName The name of the product that must be in the order.
     * @return a list of orders that contain the specified product.
     *
     */
    @Override
    public List<Order> findByProduct(String productName) {
        return orderRepository.findAll().stream()
                .filter(o -> o.products().stream().anyMatch(p -> p.name().equals(productName)))
                .toList();
    }


    /**
     * Return orders paginated according to the parameters page and size.
     * Example: If there are 10 orders & we want page 2, taken in batches of 2, we should return
     * elements 2 and 3 from the list [0,1,2,3,4,5,6,7,8,9].*
     * @param page the page number, starting from 1.
     * @param size the number of orders per page.
     * @return a list of orders corresponding to the specified page and size.
     *
     */
    @Override
    public List<Order> findByPage(int page, int size) {
        if (page <= 0 || size <= 0) {
            throw new IllegalArgumentException("Page and size parameters must be positive");
        }

        int skipCount = (page - 1) * size; //Pages to be skipped

        return orderRepository.findAll().stream()
                .skip(skipCount) // Skips elements corresponding to previous pages
                .limit(size)    // Limits the number of elements in the current page
                .toList(); // Collects elements into a list
    }

    /**
     * Return the average delivery time (in days) for orders delivered within a specific time range.
     *
     * @param from The starting date of the time range.
     * @param to   The ending date of the time range.
     * @return the average delivery time (in days) for orders delivered within the specified time range.
     */
    @Override
    public OptionalDouble findAverageDelivery(LocalDateTime from, LocalDateTime to) {
        return orderRepository.findAll().stream()
                .filter(o -> o.status() == OrderStatus.DELIVERED)
                .filter(o -> o.date().isAfter(from) || o.date().isEqual(from))
                .filter(o -> o.date().isBefore(to) || o.date().isEqual(to))
                .mapToDouble(order -> ChronoUnit.DAYS.between(order.date(), order.deliveryDate()))
                .average();
    }

    /**
     * Return the total value of orders for a specific customer.
     * @param customerName The name of the customer.
     * @return the total value of orders corresponding to the specified customer.
     *
     */
    @Override
    public BigDecimal findTotalByCustomer(String customerName) {
        return orderRepository.findAll().stream()
                .filter(o -> o.customer().equals(customerName))
                .map(Order::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Return a list of names of products that have been sold, without duplicates.
     * @return a list of names of products that have been sold, without duplicates.
     */
    @Override
    public List<String> findSoldProducts() {
        return orderRepository.findAll().stream()
                .filter(o -> o.status() != OrderStatus.CANCELLED)
                .flatMap(o -> o.products().stream())
                .map(Product::name)
                .distinct()
                .toList();
    }

    /**
     * Return a list of last delivered orders, limited by the specified limit.
     *
     * @param limit The maximum number of orders to return.
     * @return A list of last delivered orders, limited by the specified limit.
     */
    @Override
    public List<Order> findLastDeliveredOrders(Integer limit) {
        return orderRepository.findAll().stream()
                .filter(o -> o.status() .equals(OrderStatus.DELIVERED))
                .sorted(Comparator.comparing(Order::deliveryDate).reversed())
                .limit(limit).toList();
    }

    /**
     * Return a map where each key is an order and the associated value is the count of products in that order.
     * @return A map where each key is an order and the associated value is the count of products in that order.
     */
    @Override
    public Map<Order, Integer> findOrderProductCount() {
        return orderRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        o -> o,
                        Collectors.summingInt(o -> o.products().size()))
                );
    }

    /**
     * Returns a Map associating each customer with the total amount they spent.
     * @return Map associating each customer with the total amount they spent.
     */
    @Override
    public Map<String, BigDecimal> findTotalByCustomer() {
        return orderRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Order::customer,
                        Collectors.mapping(Order::total, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));
    }

}