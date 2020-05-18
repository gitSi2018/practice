package com.si.practice.client.order.api;

import com.si.practice.common.order.OrderDTO;

import java.util.List;

public interface OrderCreateService {

    Long createOrder(OrderDTO orderDTO);

    Boolean batchInsert(List<OrderDTO> orderDTOS);
}
