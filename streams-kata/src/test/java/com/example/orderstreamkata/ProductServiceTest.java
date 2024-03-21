package com.example.orderstreamkata;

import com.example.orderstreamkata.model.Product;
import com.example.orderstreamkata.repository.OrderLocalRepository;
import com.example.orderstreamkata.service.OrderServiceImpl;
import com.example.orderstreamkata.service.ProductServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductServiceTest {
    private ProductServiceImpl productServiceImpl = new ProductServiceImpl(OrderLocalRepository.getInstance());

    @Before
    public void setUp() {
        OrderLocalRepository.getInstance().reset();
    }

    @Test
    public void should_found_product_1_and_4_results() {
        var products = productServiceImpl.findByCategoryAndPrice("Category1", BigDecimal.valueOf(2.5));
        Assert.assertEquals(4, products.size());
    }

    @Test
    public void should_found_product_and_4_results() {
        var products = productServiceImpl.findByCategoryAndPrice("Category3", BigDecimal.valueOf(4));
        Assert.assertEquals(5, products.size());
    }

    @Test
    public void should_found_product_1__results() {
        var product = productServiceImpl.findCheapestByCategory("Category1");
        assertEquals("Product2", product.name());
        System.out.println();
    }

    @Test
    public void should_found_product__results() {
        var product = productServiceImpl.findCheapestByCategory("Category3");
        assertEquals("Product3", product.name());
    }

    @Test
    public void findExpensiveProductByCategory() {
        var product = productServiceImpl.findExpensiveProductByCategory("Category1");
        assertEquals("Product1", product.name());
    }

    @Test
    public void findExpensiveProduc3tByCategory() {
        var product = productServiceImpl.findExpensiveProductByCategory("Category3");
        assertEquals("Product3", product.name());
    }

}