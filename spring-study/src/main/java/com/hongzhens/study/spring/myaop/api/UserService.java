package com.hongzhens.study.spring.myaop.api;

/**
 * @author: HongZhenSi
 * @date: 2020/11/16
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public interface UserService {


    void update(Long id);

    Long add(String name, Integer age);
}
