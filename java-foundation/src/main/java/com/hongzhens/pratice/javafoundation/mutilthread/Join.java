package com.hongzhens.pratice.javafoundation.mutilthread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author: HongZhenSi
 * @date: 2020/12/7
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
public class Join {


    private static ThreadFactory threadFactory = r -> new Thread(r, "Join-task");



    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1,
            3,
            10000L,
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<Runnable>(10),
//            threadFactory,
            r -> new Thread(r, "Join-task"),
            new ThreadPoolExecutor.AbortPolicy()
    );

    private static void joinTest() throws InterruptedException {

        Thread a = new Thread(() -> log.info("helloA"));
        Thread b = new Thread(() -> log.info("helloB"));
        a.start();
        b.start();
        a.join();
        b.join();
        // 当前线程等待着a、b执行完成之后执行
        log.info("joinTest 1");


        // 使用了线程池之后， join的特性看起来失效了 why？ 因为 join方法调用的时候，
        // 会检查调用调用线程是否是isAlive的，如果没有start，这个条件并不满足，因此当前线程不会真正的等待。
        // 线程池会用他自己的线程来执行传入的runnable
        Thread c = new Thread(() -> log.info("helloC, {}", Thread.currentThread().getName()), "C");
        Thread d = new Thread(() -> log.info("helloD, {}", Thread.currentThread().getName()), "D");
        executor.submit(c);
        executor.submit(d);
        c.join();
        d.join();
        log.info("joinTest 2");
    }


    private static void threeWorkNeedDo() throws InterruptedException {

//        Thread a = new Thread(() -> log.info("A"));
//        Thread b = new Thread(() -> log.info("B"));
//        Thread c = new Thread(() -> log.info("C"));
//        a.start(); b.start(); c.start();
//        a.join(); b.join(); c.join();
        // 通过join的方法每次都需要新的线程，创建线程又是一件耗费资源的事情。如果使用线程池的话，又不能使用join。
        // 因此，join不是一个很好的方式。使用 countDownLatch
        CountDownLatch downLatch = new CountDownLatch(3);
        downLatch.countDown();
        executor.execute(() ->  {log.info("A1"); downLatch.countDown();});
        executor.execute(() ->  {log.info("B1"); downLatch.countDown();});
        executor.execute(() ->  {log.info("C1"); downLatch.countDown();});
        // await()，等待downLatch的count为0（或者interrupt了），才会让当前线程继续往下执行。 注意和 wait()（这是Object中的）区分
        downLatch.await();
        log.info("hello threeWorkNeedDo");
    }



    public static void main(String[] args) throws InterruptedException {

//        joinTest();
        threeWorkNeedDo();
    }
}
