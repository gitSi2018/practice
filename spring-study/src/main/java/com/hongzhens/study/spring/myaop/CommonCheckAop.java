package com.hongzhens.study.spring.myaop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author: HongZhenSi
 * @date: 2020/11/16
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */

//springboot默认使用的是 CGLib 代理
@Component
@Slf4j
//@Aspect 注解定义切面，但是在没有用xml进行对应的配置时，则会还需要使用@Component 才能被spring容器注入
@Aspect
public class CommonCheckAop {


    // 定义切点
    @Pointcut("@annotation(com.hongzhens.study.spring.myaop.myannotion.CommonMethodCheck)")
    public void checkPoint(){

    }


    @Before("checkPoint()")
    public void beforeCheck(){

        log.info("CommonCheckAop beforeCheck");
    }


    @After("checkPoint()")
    public void afterCheck(){
        log.info("CommonCheckAop afterCheck");
    }

    //先进入 around、在进入before(), 再执行 joinPoint.proceed() ，最后再执行 after()
    @Around("checkPoint()")
    public Object aroundCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("CommonCheckAop aroundCheck. joinPoint:{}", joinPoint);
        return joinPoint.proceed();
    }


}
