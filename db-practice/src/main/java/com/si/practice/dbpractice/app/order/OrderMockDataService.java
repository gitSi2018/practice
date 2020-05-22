package com.si.practice.dbpractice.app.order;

import com.si.practice.dbpractice.common.order.OrderDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMockDataService {

    public List<OrderDTO> generateOrder(int size, long maxProdId){

        List<OrderDTO> orderDTOS = new ArrayList<>(size);
        for (int i = 0; i < size; i++){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setDriverId(i + 1000L);
            orderDTO.setPassenger("name0:" + i) ;
            orderDTO.setProdId(getRandomProdId(maxProdId));
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }

    private Long getRandomProdId(long maxProdId){

        return (long)(Math.random() * maxProdId);
    }
}
