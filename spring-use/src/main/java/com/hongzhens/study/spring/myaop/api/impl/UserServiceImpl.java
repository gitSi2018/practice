package com.hongzhens.study.spring.myaop.api.impl;

import com.hongzhens.study.spring.myaop.api.UserService;
import com.hongzhens.study.spring.myaop.api.entity.dto.InsertDTO;
import com.hongzhens.study.spring.myaop.myannotion.CommonMethodCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;

/**
 * @author: HongZhenSi
 * @date: 2020/11/16
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
public class UserServiceImpl
        implements UserService {


    @CommonMethodCheck
    @Override
    public void update(Long id) {

        log.info("UserServiceImpl update, id:{}", id);
    }


    @CommonMethodCheck
    @Override
    public Long add(String name, Integer age) {

        log.info("UserServiceImpl add, name:{}, age:{}", name, age);
        ((UserService)AopContext.currentProxy()).update(1L);
        return 1L;
    }

    @CommonMethodCheck
    String getName(Long id){

        log.info("UserServiceImpl getName, id:{}", id);
        return "张三";
    }
}
