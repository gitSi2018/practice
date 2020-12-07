package com.hongzhens.study.spring.myaop.myannotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: HongZhenSi
 * @date: 2020/11/24
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterValid {
}
