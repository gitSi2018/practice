package com.hongzhens.pratice.common.utils;

/**
 * @author: HongZhenSi
 * @date: 2020/12/10
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
public class MRandomUtils {


    public static int random(int min, int range){

        return  (int)(Math.random() * range) + min;
    }
}
