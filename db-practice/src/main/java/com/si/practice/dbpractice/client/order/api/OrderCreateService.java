package com.si.practice.dbpractice.client.order.api;


import com.si.practice.dbpractice.common.order.OrderDTO;

import java.util.List;

public interface OrderCreateService {

    Long createOrder(OrderDTO orderDTO);

    Boolean batchInsert(List<OrderDTO> orderDTOS);
}
