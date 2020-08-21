package com.hongzhens.pratice.javafoundation.mutilthread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: HongZhenSi
 * @date: 2020/8/10
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
public class SimpleTest {

    public static void main(String[] args) {

        new Thread(() ->{
            int count = 0;
            while (count++ < 10) {
                log.info("hello thread running!");
            }
        }).start();
    }
}
