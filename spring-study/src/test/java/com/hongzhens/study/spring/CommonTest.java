package com.hongzhens.study.spring;

import com.hongzhens.study.spring.myaop.api.UserService;
import com.hongzhens.study.spring.myaop.api.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: HongZhenSi
 * @date: 2020/11/16
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class CommonTest {


    @Resource
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void commonCheck1(){

        log.info("result:{}", userService.add("zhangsan", 1));
    }

    @Test
    public void commonCheck2(){

        userService.update( 1L);
    }


    @Test
    public void commonCheck3(){

        log.info("result:{}", userServiceImpl.add("zhangsan", 1));
    }

    @Test
    public void commonCheck4(){

        userServiceImpl.update( 1L);
    }

//    @Test
//    public void commonCheck3(){
//
//        userService.getName(1L);
//    }
}
