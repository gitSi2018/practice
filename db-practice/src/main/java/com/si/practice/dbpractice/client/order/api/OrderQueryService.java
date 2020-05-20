package com.si.practice.dbpractice.client.order.api;


import com.si.practice.dbpractice.client.order.request.QueryOrderRequest;
import com.si.practice.dbpractice.common.order.OrderDTO;

import java.util.List;

public interface OrderQueryService {

    OrderDTO queryOrderById(Long orderId);

    List<OrderDTO> listOrders(QueryOrderRequest request);
}
