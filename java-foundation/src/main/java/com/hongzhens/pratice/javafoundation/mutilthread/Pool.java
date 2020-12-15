package com.hongzhens.pratice.javafoundation.mutilthread;

import com.hongzhens.pratice.common.utils.MyThreadUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: HongZhenSi
 * @date: 2020/12/13
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
public class Pool {


    private static final ThreadFactory factory  = (r) -> new Thread(r, "A");

    //如果核心线程为1，则当提交的任务未将等待队列占满时，此时该线程池中不会新创建非核心线程，退化成单线程的执行
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            1, // 先往这里加
            50, //线程池大小 在任务等待队列都满了之后再增加非核心线程
            10, // 非核心线程存活时间
            TimeUnit.MICROSECONDS,
            new LinkedBlockingDeque<>(50), //阻塞队列，注意，最好使用有界的阻塞队列
            factory, // 线程池中的线程工厂，主要是让线程池中的线程名字有意义
            new ThreadPoolExecutor.AbortPolicy() //拒绝策略
    );

    public static void main(String[] args) throws Exception{

        testThreadPoolExecutorAdd();
//        futureUse1();
//        futureUse2();
//        execOrderUse1();
//        completableFutureUse1();
    }


    private static void testThreadPoolExecutorAdd(){

        testAdd(50);

        int a = 0;
        for (int i = 0; i < 10; i++){
            try {

                log.info("workQueue size:{}", ((ThreadPoolExecutor)executor).getQueue().size());
                log.info("worker size:{}", ((ThreadPoolExecutor)executor).getPoolSize());
                Thread.sleep(100);
            }catch (InterruptedException e){
                log.error("interrupted.", e);
            }
        }
    }

    private static void testAdd(int size){

        AtomicInteger atomicInteger = new AtomicInteger(0);
        while (size > 0 ){
            size--;
            try {
                executor.execute(factory.newThread(() -> log.info("hello:{}", atomicInteger.incrementAndGet())));
            }catch (Exception e){

                log.error("too many", e);
            }
        }
    }

    // 两个不同的future提交给线程池运行之后，他们之间是并发执行的，如果某个线程中调用了某个future的get.
    // futureTask 怎么实现get的阻塞。futureTask 定义了一个 state，用于表示任务的状态。
    // 正常执行完之后会将该状态设置为 NORMAL，并且将执行结果设置到 Object outcome中，对于Runnable的任务，这个值为null。在执行get的时候，会检查该状态，并且阻塞在这里，直到status > COMPLETING
    // 从而实现获取到数据之前阻塞
    public static void futureUse1() throws ExecutionException, InterruptedException {

        long startTime = System.currentTimeMillis();
        FutureTask<Integer> futureAge = new FutureTask<>(() -> {
            Thread.sleep(10000L);
            log.info("age");
            return 5;});
        FutureTask<String> futureNAme = new FutureTask<>(() -> {
            Thread.sleep(5000L);
            log.info("name");
            return "张三";
        });
        executor.execute(futureAge);

        executor.execute(futureNAme);

        int age = 0;
        age = futureAge.get();
        int age2 = futureAge.get();
        log.info("age:{}, age2:{}, runningTime:{}", age, age2, (System.currentTimeMillis() - startTime) / 1000);
        String name = "";
        name = futureNAme.get();
        log.info("age:{}, name:{}, runningTime:{}", age, name, (System.currentTimeMillis() - startTime) / 1000);
    }

    private static void futureUse2() throws Exception{

        long startTime = System.currentTimeMillis();
        Future<Integer> futureAge = executor.submit(() -> {

            Thread.sleep(5000L);
            log.info("age");
            return 10;
        });

        Future<String> futureName = executor.submit(() ->{
            Thread.sleep(10000L);
            log.info("name");
            return "王五";
        });

        int age = 0;
        age = futureAge.get();
        log.info("age:{} runningTime:{}", age, (System.currentTimeMillis() - startTime) / 1000);
        String name = "";
        name = futureName.get();
        log.info("name:{} runningTime:{}", name, (System.currentTimeMillis() - startTime) / 1000);

    }

    private static void execOrderUse1() throws Exception{

        AtomicInteger size = new AtomicInteger();
        for (int i = 0; i < 50; i++){
            executor.execute(() -> log.info("第 {} 个", size.incrementAndGet()));
        }
    }

    //对比Future，已经没有显示使用线程池了，更关注每个任务的逻辑，已经这些任务的协调
    public static void completableFutureUse1(){


        //任务1：洗水壶->烧开水
        CompletableFuture<String> f1 =
                //supplyAsync 是有返回的,  runAsync 是没有返回的
                CompletableFuture.supplyAsync(() -> {
                    log.info("T1: 洗水壶");
                    MyThreadUtils.sleepWithOutInterruptedException(1,TimeUnit.SECONDS);
                    log.info("T1: 烧水");
                    MyThreadUtils.sleepWithOutInterruptedException(15, TimeUnit.SECONDS);
                    return "洗完";
                });
        //任务二：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() ->{
                    log.info("T2: 洗茶壶");
                    MyThreadUtils.sleepWithOutInterruptedException(1, TimeUnit.SECONDS);
                    log.info("T2: 洗茶杯");
                    MyThreadUtils.sleepWithOutInterruptedException(2, TimeUnit.SECONDS);
                    log.info("T2: 拿茶叶");
                    MyThreadUtils.sleepWithOutInterruptedException(1, TimeUnit.SECONDS);
                    return "龙井";
                });


        CompletableFuture<String> f3 =
                // f1和f2并发执行。 tf1是f1的返回， tf2是f2的返回。如果没有返回可用 __ 作为参数
                f1.thenCombine(f2, (tf1, tf2) ->{
            log.info("T1拿到茶叶：{}, tf1:{}", tf2, tf1);
            return "上茶：" + tf2;
        });
        log.info("f3 join:{}", f3.join());

    }




}
