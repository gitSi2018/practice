package com.hongzhens.pratice.javafoundation.mutilthread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author HongZhenSi
 * @date 2020/8/10
 * @modifiedBy
 * @description: 多个线程严格按照指定的顺序交替处理
 * @version 1.0
 */
@Slf4j
public class AlternateDeal {

    private volatile static int dealCount;


    public static void main(String[] args) throws Exception{
//        towThreadsAlternate(1000 * 1000);
//        towThreadsAlternate(26);
        towThreadsAlternateWithTotalTime(26);
//        towThreadsAlternateWithTotalTime(1000 * 1000);
        log.info("end");
    }

    /** 两个线程访问同一个变量 dealCount，但是这里却没有线程安全问题。值得深入探讨
     * 1. 起初，使用了synchronized 对dealCount的访问加了锁。毫无疑问，对dealCount的访问和修改是线程安全的。但是，
     * 代码运行的时间确是非常长的，每次线程通过竞争拿到对dealCount访问的锁之后，可能不满足处理条件，导致浪费了这次费力不少
     * 力气获得的锁（numberSize ）  -> 那么，可以借鉴双重检查锁的思想
     * @param numberSize
     */
    public static void towThreadsAlternate(int numberSize){

        new Thread(() -> {
            int count = 0;
            long wasteCpuCount = 0;
            while (count < numberSize) {
//                synchronized (AlternateDeal.class) {
                    if (dealCount % 2 == 0) {
//                        log.info("threadA:{}", ++count);
                        dealCount++;
                        continue;
                    }
                    wasteCpuCount++;
//                    log.debug("threadA waste cpu count:{}", ++wasteCpuCount);
//                }
            }
            log.info("threadA waste count:{}", wasteCpuCount);
        }).start();

        new Thread(() -> {
            int count = 0;
            long wasteCpuCount = 0;
            while (count < numberSize){
//                synchronized (AlternateDeal.class){
                    if (dealCount % 2 == 1){
//                        log.info("ThreadB:{}", (char) (++count + 64));
                        dealCount ++;
                        continue;
                    }
                    wasteCpuCount++;
//                    log.debug("threadB waste cpu count:{}", ++wasteCpuCount);
//                }
            }
            log.info("threadB waste count:{}", wasteCpuCount);
        }).start();
    }


    public static long towThreadsAlternateWithTotalTime(int numberSize) throws ExecutionException, InterruptedException {

        long startTime = System.currentTimeMillis();

        Future<Long> futureA = Executors.newSingleThreadExecutor().submit(() -> {
            log.debug("start Thread A:{}", System.currentTimeMillis());
            long innerStartTime = System.currentTimeMillis();
            int count = 0;
            long wasteCpuCount = 0;
            while (count < numberSize){
//                synchronized (AlternateDeal.class){
                    if (dealCount % 2 == 0){
                        dealCount++;
                        count++;
                        log.debug("ThreadA:{}", count);
                        continue;
                    }
                    wasteCpuCount++;
//                }
            }
            log.info("threadA waste count:{}", wasteCpuCount);
            return System.currentTimeMillis() - innerStartTime;
        });

        Future<Long> futureB = Executors.newSingleThreadExecutor().submit( () ->{
            log.debug("start Thread B:{}", System.currentTimeMillis());
            int count = 0;
            long innerStartTime = System.currentTimeMillis();
            long wasteCpuCount = 0;
            while (count < numberSize){
//                synchronized (AlternateDeal.class){
                    if (dealCount % 2 == 1){
                        dealCount++;
                        count++;
                        log.debug("ThreadB:{}", (char)(count + 64));
                        continue;
                    }
                    wasteCpuCount++;
//                }
            }
            log.info("threadB waste count:{}", wasteCpuCount);
            return System.currentTimeMillis() - innerStartTime;
        });

        log.info("ThreadA running time:{}", futureA.get());
        log.info("ThreadB running time:{}", futureB.get());
        long totalTime = System.currentTimeMillis() - startTime;
        log.info("total running time:{}", totalTime);
        return totalTime;
    }
}
