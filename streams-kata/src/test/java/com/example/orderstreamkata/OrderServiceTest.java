package com.example.orderstreamkata;

import com.example.orderstreamkata.model.Order;
import com.example.orderstreamkata.model.OrderStatus;
import com.example.orderstreamkata.model.Product;
import com.example.orderstreamkata.repository.OrderLocalRepository;
import com.example.orderstreamkata.service.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderServiceTest {
    private OrderServiceImpl orderServiceImpl = new OrderServiceImpl(OrderLocalRepository.getInstance());

    @Test
    public void findAll_ordersNotEmptyAndCorrectSize() {
        var orders = orderServiceImpl.findAll();
        Assertions.assertThat(orders)
                .isNotEmpty()
                .hasSize(4);
    }

    @Before
    public void setUp() {
        OrderLocalRepository.getInstance().reset();
    }

    @Test
    public void findByProduct_product1FoundAndCorrectResults() {
        var products = orderServiceImpl.findByProduct("Product1");
        int expectedSize = 4;
        assertEquals(expectedSize, products.size());
        assertTrue(products.stream()
                .allMatch(o -> o.products().stream().anyMatch(p -> p.name().equals("Product1"))));
    }

    @Test
    public void findByProduct_product2FoundAndCorrectResults() {
        var products = orderServiceImpl.findByProduct("Product2");
        int expectedSize = 1;
        assertEquals(expectedSize, products.size());
        assertTrue(products.stream()
                .allMatch(o -> o.products().stream().anyMatch(p -> p.name().equals("Product2"))));
    }

    @Test
    public void findByProduct_product3FoundAndCorrectResults() {
        var products = orderServiceImpl.findByProduct("Product3");
        int expectedSize = 4;
        assertEquals(expectedSize, products.size());
        assertTrue(products.stream()
                .allMatch(o -> o.products().stream().anyMatch(p -> p.name().equals("Product3"))));
    }

    @Test
    public void findByPage_elements3And4Found() {
        var orders = orderServiceImpl.findByPage(2,2);
        int expectedSize = 2;
        assertEquals(expectedSize, orders.size());
        assertEquals(3L, orders.get(0).id());
        assertEquals(4L, orders.get(1).id());
    }

    @Test
    public void findByPage_element5Found() {
        var orders = orderServiceImpl.findByPage(2,3);
        int expectedSize = 1;
        assertEquals(expectedSize, orders.size());
        assertEquals(4L, orders.get(0).id());
    }

    @Test
    public void findByPage_noElementsFound() {
        var orders = orderServiceImpl.findByPage(3,3);
        assertEquals(0, orders.size());
    }

    @Test
    public void findTotalByCustomer_client1AmountCorrect() {
        assertEquals(BigDecimal.valueOf(27.5), orderServiceImpl.findTotalByCustomer("Customer1"));
    }

    @Test
    public void findTotalByCustomer_client2AmountCorrect() {
        assertEquals(BigDecimal.valueOf(6.5), orderServiceImpl.findTotalByCustomer("Customer2"));
    }


    @Test
    public void findAverageDelivery_shouldBe10InJanuary() {

        orderServiceImpl.add(new Order(5L, OrderStatus.DELIVERED, LocalDateTime.of(2023,01,01,0,00),
                List.of(
                        new Product(1L, "Product4", "Category1", new BigDecimal(10))
                ),
                "Customer3",
                LocalDateTime.of(2023,01,17,15,00)

        ));

        var days = orderServiceImpl.findAverageDelivery(
                LocalDateTime.of(2023,01,01,0,00),
                LocalDateTime.of(2023,01,30,0,00));

        assertEquals(4, ChronoUnit.DAYS.between(
                        LocalDateTime.of(2023,01,01,14,01),
                        LocalDateTime.of(2023,01,05,15,00)));

        assertEquals(OptionalDouble.of(10), days);
    }

    @Test
    public void findAverageDelivery_shouldBe2InJanuary() {
        orderServiceImpl.add(new Order(5L, OrderStatus.DELIVERED, LocalDateTime.of(2023,01,01,0,00),
                List.of(new Product(1L, "Product4", "Category1", new BigDecimal(10))),
                "Customer3",
                LocalDateTime.of(2023,01,17,15,00)

        ));
        orderServiceImpl.add(
                new Order(6L, OrderStatus.DELIVERED, LocalDateTime.of(2023,01,01,0,00),
                List.of(new Product(1L, "Product4", "Category1", new BigDecimal(10))),
                "Customer3",
                LocalDateTime.of(2023,02,01,0,00)
        ));

        var days = orderServiceImpl.findAverageDelivery(
                LocalDateTime.of(2023,01,01,0,00),
                LocalDateTime.of(2023,03,30,0,00));

        assertEquals(OptionalDouble.of(17), days);
    }

    @Test
    public void findSoldProducts_noCancelledProductsReturned() {
        orderServiceImpl.add(new Order(5L, OrderStatus.CANCELLED, LocalDateTime.of(2023,01,01,0,00),
                        List.of(new Product(1L, "Product_cancelled", "Category1", new BigDecimal(10))),
                        "Customer3"));

        List<String> amountOfProducts = orderServiceImpl.findSoldProducts();
        assertFalse(amountOfProducts.contains("Product_cancelled"));
        assertEquals(List.of("Product1", "Product2", "Product3"), amountOfProducts);
    }

    @Test
    public void findLastDeliveredOrders_getLastInitialOrderDelivered() {
        List<Order> latestOrder = orderServiceImpl.findLastDeliveredOrders(1);
        assertEquals(3L, latestOrder.get(0).id());
    }

    @Test
    public void findLastDeliveredOrders_getLastOrderDelivered() {
        var orderDelivered = orderServiceImpl.findAll().stream().filter(o -> o.status().equals(OrderStatus.DELIVERED)).findFirst();
        var newLastDate = orderDelivered.get().date().plus(10, ChronoUnit.DAYS);

        orderServiceImpl.add(
                new Order(6L, OrderStatus.DELIVERED, LocalDateTime.of(2023,01,01,0,00),
                        List.of(new Product(1L, "Product4", "Category1", new BigDecimal(10))),
                        "Customer3",
                        newLastDate
                ));

        List<Order> latestOrder = orderServiceImpl.findLastDeliveredOrders(1);
        assertEquals(6L, latestOrder.get(0).id());
    }

    @Test
    public void findLastDeliveredOrders_getLastOrder3Delivered() {
        var orderDelivered = orderServiceImpl.findAll().stream().filter(o -> o.status().equals(OrderStatus.DELIVERED)).findFirst();

        orderServiceImpl.add(
                new Order(6L, OrderStatus.DELIVERED, LocalDateTime.of(2023,01,01,0,00),
                        List.of(new Product(1L, "Product4", "Category1", new BigDecimal(10))),
                        "Customer3",
                        orderDelivered.get().date().plus(20, ChronoUnit.DAYS)
                ));

        orderServiceImpl.add(
                new Order(7L, OrderStatus.DELIVERED, LocalDateTime.of(2023,01,01,0,00),
                        List.of(new Product(1L, "Product4", "Category1", new BigDecimal(10))),
                        "Customer3",
                        orderDelivered.get().date().plus(10, ChronoUnit.DAYS)
                ));

        List<Order> latestOrder = orderServiceImpl.findLastDeliveredOrders(1);
        assertEquals(6L, latestOrder.get(0).id());
    }

    @Test
    public void findOrderProductCount_correctCountReturned() {
        var map = orderServiceImpl.findOrderProductCount();

        Order o1 = orderServiceImpl.getById(1L).get();
        Order o2 = orderServiceImpl.getById(2L).get();
        Order o3 = orderServiceImpl.getById(3L).get();
        Order o4 = orderServiceImpl.getById(4L).get();

        assertEquals(4, map.get(o1));
        assertEquals(3, map.get(o2));
        assertEquals(2, map.get(o3));
        assertEquals(2, map.get(o4));
    }


    @Test
    public void findTotalByClient_totalAmountByClientCorrect() {
        Map<String, BigDecimal> map = orderServiceImpl.findTotalByCustomer();
        assertEquals(BigDecimal.valueOf(27.5), map.get("Customer1"));
        assertEquals(BigDecimal.valueOf(6.5), map.get("Customer2"));
    }


}
