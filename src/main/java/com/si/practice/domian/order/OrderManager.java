package com.si.practice.domian.order;


import com.si.practice.common.order.OrderDTO;
import com.si.practice.infrature.mysql.entity.OrderEntity;
import com.si.practice.infrature.mysql.mapper.OrderBaseMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class OrderManager {

    @Resource
    private OrderBaseMapper orderBaseMapper;

    public Long createOrder(OrderDTO order){

        log.info("OrderManager createOrder, order:{}", order);
        OrderEntity entity = convert(order);
        Date now = new Date();
        entity.setCreated(now);
        entity.setUpdated(now);
        orderBaseMapper.insertSelective(entity);
        log.info("OrderManager createOrder, entity:{}", entity);
        return entity.getId();
    }


    public OrderDTO queryOrderById(Long orderId){

        return convert(queryOrderEntityById(orderId));
    }

    private OrderEntity queryOrderEntityById(Long orderId){
        OrderEntity entity = orderBaseMapper.selectByPrimaryKey(orderId);
//        log.info("OrderManager queryOrderEntityById, orderId:{}, entity:{}",
//                orderId, entity);
        return entity;
    }

    public boolean updateOrderStatus(Long orderId, Integer status, int retryTime){

        retryTime = Math.min(retryTime, 3);
        for (int i = 0; i < retryTime + 1; i++){
            OrderEntity entity = queryOrderEntityById(orderId);
            entity.setStatus(entity.getStatus() + status);
            if (updateWithCasByPrimaryKey(entity)){
//                log.info("OrderManager updateOrderStatus succeed. orderId:{}, status:{}, i:{}",
//                        orderId, status, i);
                return true;
            }
        }
        log.warn("OrderManager updateOrderStatus failed. orderId:{}, status:{}",
                orderId, status);
        return false;
    }

    private boolean updateWithCasByPrimaryKey(OrderEntity entity){

        Example example = new Example(OrderEntity.class);
        int preVersion = entity.getVersion();
        entity.setVersion(preVersion + 1);
        example.createCriteria().
                andEqualTo("id", entity.getId()).
                andEqualTo("version", preVersion);
        return orderBaseMapper.updateByExample(entity, example) > 0;
    }

    @Transactional
    public boolean batchInsert(List<OrderDTO> orders) throws Exception{
        if (CollectionUtils.isEmpty(orders)){

            log.warn("OrderManager batchInsert parameter error. orders:{}", orders);
            return false;
        }
        List<OrderEntity> orderEntities = convertToOrderEntity(orders);
        int maxSizeOneInsert = 100;
        Map<Long, List<OrderEntity>> listMap=
                orderEntities.stream().collect(Collectors.groupingBy
                        (orderEntity -> orderEntity.getDriverId() / maxSizeOneInsert));
        List<List<OrderEntity>> groupList = new ArrayList<>();
        Set<Long> keySet = listMap.keySet();
        for ( Long key : keySet){
            List<OrderEntity> temp = listMap.get(key);
            if (CollectionUtils.isEmpty(temp)){
                continue;
            }
            groupList.add(temp);
        }
        try {
//            orders.forEach(orderDTO -> createOrder(orderDTO));
            groupList.parallelStream().forEach( tempOrder ->
                    orderBaseMapper.batchInsert(tempOrder)
            );

            return true;
        }catch (Exception e) {
            log.error("OrderManager batchInsert failed. ", e);
            throw new Exception("batch insert failed");
        }
    }

    public OrderEntity convert(OrderDTO orderDTO){
        if (orderDTO == null){
            return null;
        }
        OrderEntity entity = new OrderEntity();
        BeanUtils.copyProperties(orderDTO, entity);
        return entity;
    }

    public OrderDTO convert(OrderEntity orderEntity){
        if (orderEntity == null){
            return null;
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderEntity, orderDTO);
        return orderDTO;
    }

    public List<OrderEntity> convertToOrderEntity(List<OrderDTO> orderDTOS){
        if (CollectionUtils.isEmpty(orderDTOS)){

            return new ArrayList<>(0);
        }
        return orderDTOS.parallelStream().map(this::convert).collect(Collectors.toList());
    }

    public List<OrderDTO> convertToOrderDTO(List<OrderEntity> orderEntities){

        if (CollectionUtils.isEmpty(orderEntities)){

            return new ArrayList<>(0);
        }
        return orderEntities.parallelStream().map(this::convert).collect(Collectors.toList());
    }

}
