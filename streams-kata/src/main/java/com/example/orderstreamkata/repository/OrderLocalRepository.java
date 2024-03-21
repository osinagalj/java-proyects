package com.example.orderstreamkata.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.orderstreamkata.model.Order;
import com.example.orderstreamkata.model.OrderStatus;
import com.example.orderstreamkata.model.Product;
import com.example.orderstreamkata.repository.OrderRepository;

public class OrderLocalRepository implements OrderRepository {

    private static OrderLocalRepository instance;

    private List<Order> orders = new ArrayList<>();

    @Override
    public Optional<Order> getById(Long id) {
        return orders.stream().filter(o -> o.id().equals(id)).findFirst();
    }
    /**
     * Private constructor to avoid direct instantiation
     */
    private OrderLocalRepository() {
        orders.addAll(initialOrders);
    }

    /**
     * Static method to get the Singleton instance
     */
    public static OrderLocalRepository getInstance() {
        if (instance == null) {
            instance = new OrderLocalRepository();
        }
        return instance;
    }

    @Override
    public List<Order> findAll() {
        return orders;
    }

    @Override
    public void add(Order order) {
        orders.add(order);
    }



    public void reset() {
        orders = new ArrayList();
        orders.addAll(initialOrders);
    }

    private List<Order> initialOrders = List.of(
            new Order(1L, OrderStatus.PENDING, LocalDateTime.of(2023,01,01,14,01),
                    List.of(
                            new Product(1L, "Product1", "Category1", new BigDecimal(2.5)),
                            new Product(2L, "Product2" , "Category1", new BigDecimal(1)),
                            new Product( 3L, "Product3", "Category2", new BigDecimal(3)),
                            new Product( 4L, "Product3", "Category3", new BigDecimal(4.0))
                    ),
                    "Customer1"

            ),
            new Order(2L, OrderStatus.IN_DELIVERY, LocalDateTime.of(2023,01,01,14,01),
                    List.of(
                            new Product(1L, "Product1", "Category1", new BigDecimal(2.5)),
                            new Product( 4L, "Product3", "Category3", new BigDecimal(4.0)),
                            new Product( 4L, "Product3", "Category3", new BigDecimal(4.0))
                    ),

                    "Customer1"
            ),
            new Order(3L, OrderStatus.DELIVERED, LocalDateTime.of(2023,01,01,14,01),
                    List.of(
                            new Product(1L, "Product1", "Category1", new BigDecimal(2.5)),
                            new Product( 4L, "Product3", "Category3", new BigDecimal(4.0))
                    ),
                    "Customer1",
                    LocalDateTime.of(2023,01,05,15,00)

            ),
            new Order(4L, OrderStatus.IN_DELIVERY, LocalDateTime.of(2023,01,5,14,01),
                    List.of(
                            new Product(1L, "Product1", "Category1", new BigDecimal(2.5)),
                            new Product( 4L, "Product3", "Category3", new BigDecimal(4.0))
                    ),
                    "Customer2"
            ));

}
