package com.si.practice.dbpractice.client.order.api;

public interface OrderUpdateService {

    Boolean updateOrderById(Long orderId, Integer status);
}
