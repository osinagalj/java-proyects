package com.example.orderstreamkata.repository;

import com.example.orderstreamkata.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    //Buscar todos los productos que tengan determinada categoria y un minimo de precio
    List<Product> findByCategoryAndPrice(String category, BigDecimal minPrice);
    // Devolver el producto mas barato de una categoria pasada por parametro
    Product findCheapestByCategory(String category);
    // Devolver el producto mas caro de una categoria pasada por parametro
    Product findExpensiveProductByCategory(String category);
}
