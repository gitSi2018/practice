package com.si.practice.client.order.api;

public interface OrderUpdateService {

    Boolean updateOrderById(Long orderId, Integer status);
}
