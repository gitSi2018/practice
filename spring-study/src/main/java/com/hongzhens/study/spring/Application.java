package com.hongzhens.study.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: HongZhenSi
 * @date: 2020/11/16
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */

@EnableAspectJAutoProxy(exposeProxy = true)
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
