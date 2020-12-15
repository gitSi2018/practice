package com.hongzhens.pratice.common.utils;


import java.util.concurrent.TimeUnit;

/**
 * @author: HongZhenSi
 * @date: 2020/12/15
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */

public class MyThreadUtils {


    public static void sleepWithOutInterruptedException(int time, TimeUnit timeUnit){

        try {
            timeUnit.sleep(time);
        }catch (InterruptedException e){

        }
    }
}
