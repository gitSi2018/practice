package com.si.practice.infrature.mysql.mapper;

import com.si.practice.infrature.mysql.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderBaseMapper extends Mapper<OrderEntity>{

    void batchInsert(@Param("orders")List< OrderEntity> orders);
}
