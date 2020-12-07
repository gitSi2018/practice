package com.hongzhens.study.spring;

import com.hongzhens.study.spring.myaop.api.UserService;
import com.hongzhens.study.spring.myaop.api.entity.dto.InsertDTO;
import com.hongzhens.study.spring.myaop.api.impl.UserServiceImpl;
import com.hongzhens.study.spring.myaop.api.manager.UserManager;
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


//    @Resource
//    private UserService userService;

//    @Autowired
//    private UserServiceImpl userServiceImpl;

    @Autowired
    private UserServiceImpl userService;

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

        log.info("result:{}", userService.add("zhangsan", 1));
    }

    @Test
    public void commonCheck4(){

        userService.update( 1L);
    }

    @Resource
    private UserManager userManager;

    @Test
    public void parameterTest(){

        InsertDTO dto = new InsertDTO(null, "张", 21);
        userManager.insert(dto);
    }



//    @Test
//    public void commonCheck3(){
//
//        userService.getName(1L);
//    }
}
