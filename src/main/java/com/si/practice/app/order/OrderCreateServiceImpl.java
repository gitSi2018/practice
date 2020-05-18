package com.si.practice.app.order;

import com.si.practice.client.order.api.OrderCreateService;
import com.si.practice.common.order.OrderDTO;
import com.si.practice.domian.order.OrderManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class OrderCreateServiceImpl implements OrderCreateService {

    @Resource
    private OrderManager orderManager;

    @Override
    public Long createOrder(OrderDTO orderDTO) {

        return orderManager.createOrder(orderDTO);
    }

    @Override
    public Boolean batchInsert(List<OrderDTO> orderDTOS) {

        try {
            return orderManager.batchInsert(orderDTOS);
        }catch (Exception e){
            log.error("OrderCreateServiceImpl batchInsert failed. ", e);
            return false;
        }
    }
}
