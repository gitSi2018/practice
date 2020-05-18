package com.si.practice.client.order.api;

import com.si.practice.client.order.request.QueryOrderRequest;
import com.si.practice.common.order.OrderDTO;

import java.util.List;

public interface OrderQueryService {

    OrderDTO queryOrderById(Long orderId);

    List<OrderDTO> listOrders(QueryOrderRequest request);
}
