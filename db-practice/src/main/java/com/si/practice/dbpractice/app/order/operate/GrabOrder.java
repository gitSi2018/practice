package com.si.practice.dbpractice.app.order.operate;


import com.si.practice.dbpractice.client.driver.api.QueryDriverWorkInfoService;
import com.si.practice.dbpractice.client.driver.api.UpdateDriverWorkInfoService;
import com.si.practice.dbpractice.client.order.api.OrderQueryService;
import com.si.practice.dbpractice.client.order.api.OrderUpdateService;
import com.si.practice.dbpractice.common.driver.DriverWorkInfoDTO;
import com.si.practice.dbpractice.common.order.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class GrabOrder {

    @Resource
    private UpdateDriverWorkInfoService updateDriverWorkInfoService;

    @Resource
    private QueryDriverWorkInfoService queryDriverWorkInfoService;

    @Resource
    private OrderQueryService orderQueryService;

    @Resource
    private OrderUpdateService orderUpdateService;

    public boolean grabOrder(Long orderId, Long driverId){

        OrderDTO orderDTO  = orderQueryService.queryOrderById(orderId);
        if (orderDTO == null){
            log.warn("no order found. orderId:{}", orderId);
            return false;
        }
        DriverWorkInfoDTO workInfoDTO = queryDriverWorkInfoService.queryDriverWorkInfoById(driverId);
        if (workInfoDTO == null){
            log.warn("no driver found. driverID:{}", driverId);
            return false;
        }
        return true;
    }
}
