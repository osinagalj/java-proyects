package com.example.orderstreamkata.repository;


import com.example.orderstreamkata.model.Order;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

public interface OrderService {

    // Buscar las ordenes que contengan un producto
    List<Order> findByProduct(String productName);
    //Devolver las ordenes pero paginadas  segun lo que requieran los parametros page y qty
    //Ejemplo : Si hay 10 ordenes y queremos la pagina 2, tomadas de a 2 , deberiamos devolver
    //el elemento 2 y 3 de la lista [0,1,2,3,4,5,6,7,8,9]
    List<Order> findByPage(int page, int size);
    // Devolver el promedio de dias en que las ordenes son entregadas , en un rango de fechas
    OptionalDouble findAverageDelivery(LocalDateTime from , LocalDateTime to);
    // Devolver el total gastado por un cliente
    BigDecimal findTotalByCustomer(String customerName);
    //Devolver una lista de los nombres de productos que han sido vendidos, sin repetidos
    List<String> findSoldProducts();
    // Devolver las ultimas X ordenes entregadas
    List<Order> findLastDeliveredOrders(Integer limit);
    //Devoler un Map con Ordenes y la cantidad de productos vendidos
    Map<Order, Integer> findOrderProductCount();
    //Devoler un Map con los clientes y cuanto gastaron
    Map<String,  BigDecimal> findTotalByCustomer();


}
