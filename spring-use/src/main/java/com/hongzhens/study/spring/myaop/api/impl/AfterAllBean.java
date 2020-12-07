package com.hongzhens.study.spring.myaop.api.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author: HongZhenSi
 * @date: 2020/11/20
 * @modifiedBy:
 * @description:
 * @version: 1.0
 *
 * @url https://blog.csdn.net/liyantianmin/article/details/81017960
 *  ApplicationListener 加上 各种事件 实现ApplicationContext的事件机制
 *
 *  Spring的内置事件：
 *  1. ContextRefreshedEvent
 *    触发机制：
 *     ApplicationContext 初始化（所有的bean被成功装载，后处理bean被检测并激活，所有singleton bean被预实例化，ApplicationContexto容器已经就绪）或者被刷新（）时触发。
 *     或者 ConfigurableApplicationContext接口中使用 refresh()
 *
 *  2. ContextStartedEvent
 *    触发机制：ConfigurableApplicationContext(ApplicationContext的子接口)接口中的start() 方法启动ApplicationContext时触发
 *
 *  3. ContextStoppedEvent
 *    触发机制：ConfigurableApplicationContext(ApplicationContext的子接口)接口中的stop()方法停止ApplicationContext时触发，
 *    可以监听这个事件做一些清理工作
 *
 *  4. ContextClosedEvent
 *     触发机制：ConfigurableApplicationContext接口中的close()方法关闭ApplicationContext时触发，一个上下文被关闭之后已经到达生命的周期的末端，无法再被启动或刷新
 *
 *  5. RequestHandledEvent
 *    触发机制：这是一个 http-specific 事件，告诉所有的 bean HTTP请求已经被服务。注意只能应用于使用DispatcherServlet的web
 *
 */
@Slf4j
@Component
public class AfterAllBean implements ApplicationListener<ContextRefreshedEvent> {


    @Resource
    private UserServiceImpl userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        log.info("all bean has been installed");
        userService.getName(1L);
    }
}
