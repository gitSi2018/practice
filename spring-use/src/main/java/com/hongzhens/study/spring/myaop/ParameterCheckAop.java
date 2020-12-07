package com.hongzhens.study.spring.myaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: HongZhenSi
 * @date: 2020/11/24
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */

@Slf4j
@Component
//@Aspect
public class ParameterCheckAop {


//    @Pointcut("args(com.hongzhens.study.spring.myaop.myannotion.ParameterValid)")
    public void cut(){

    }

//    @Around("cut()")
    public Object aroundCheck(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("ParameterCheckAop aroundCheck. joinPoint:{}", joinPoint);
        return joinPoint.proceed();
    }

}
