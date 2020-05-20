package com.si.practice.dbpractice.app.order;


import com.si.practice.dbpractice.client.order.api.OrderCreateService;
import com.si.practice.dbpractice.client.order.api.OrderQueryService;
import com.si.practice.dbpractice.client.order.api.OrderUpdateService;
import com.si.practice.dbpractice.common.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderTest {


    @Resource
    private OrderCreateService orderCreateService;

    @Resource
    private OrderQueryService queryService;

    @Resource
    private OrderUpdateService orderUpdateService;

    @Test
    public void test(){

        log.info("hello");
    }

    /**
     * initOrder 初始化订单信息。需要用的时候打开 @Before的注释。
     *
     */
//    @Before
    public void initOrder(){

        List<OrderDTO> orderDTOS = new ArrayList<>(10);
        for (int i = 0; i < 10; i++){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setDriverId(i + 1000L);
            orderDTO.setPassenger("name0:" + i) ;
            orderDTOS.add(orderDTO);
        }
        orderCreateService.batchInsert(orderDTOS);
    }

    /**
     * 测试乐观锁。测试时initOrder。
     */
    @Test
    public void updateTest(){

//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor();
        int countSize = 1000;
        List<UpdateData> updateDatas = new ArrayList<>(countSize);
        for (int i = 0; i < countSize; i++){
            updateDatas.add(new UpdateData(8L, 1));
        }

        updateDatas.parallelStream().forEach(order ->{
            orderUpdateService.updateOrderById(order.getId(), order.getStatus());
        });

    }


    /**
     * 批量插入测试
     */
    @Test
    public void bitchInsertTest(){

        List<OrderDTO> orderDTOS = new ArrayList<>(10);
        for (int i = 0; i < 1000; i++){
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setDriverId(i + 1000L);
            orderDTO.setPassenger("name0:" + i) ;
            orderDTOS.add(orderDTO);
        }
        orderCreateService.batchInsert(orderDTOS);
    }

    @Test
    public void queryTest(){

        OrderDTO orderDTO = queryService.queryOrderById(1L);
        log.info("orderDTO:{}", orderDTO);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class UpdateData{
        private Long id;

        private Integer status;
    }

}
