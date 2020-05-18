package com.si.practice.app.task;

import com.si.practice.app.order.OrderMockDataService;
import com.si.practice.client.order.api.OrderCreateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MigrationOrderDataTask {

    @Resource
    private OrderCreateService orderCreateService;

    @Resource
    private OrderMockDataService orderMockDataService;

    private static final int size = 1000;
    private static final long maxProdId = 10000 * 50;

    @Scheduled(cron = "0/5 * * * * ?")
    public void migrateOrder(){
        log.info("migrateOrder");
        orderCreateService.batchInsert(orderMockDataService.generateOrder(size, maxProdId));
    }
}
