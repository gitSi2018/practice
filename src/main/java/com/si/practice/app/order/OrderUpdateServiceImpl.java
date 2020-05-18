package com.si.practice.app.order;

import com.si.practice.client.order.api.OrderUpdateService;
import com.si.practice.domian.order.OrderManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderUpdateServiceImpl implements OrderUpdateService {

    @Resource
    private OrderManager orderManager;

    @Override
    public Boolean updateOrderById(Long orderId, Integer status) {

        return orderManager.updateOrderStatus(orderId, status, 3);
    }
}
