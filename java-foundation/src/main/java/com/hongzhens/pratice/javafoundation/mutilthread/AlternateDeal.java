package com.hongzhens.pratice.javafoundation.mutilthread;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
//        towThreadsAlternateWithTotalTime(26);
//        towThreadsAlternateWithTotalTime(1000 * 1000);
//        new Thread(new NumberA()).start();
//        new Thread(new NumberB()).start();

//        log.info("end.:{}", (char)(65+25));

//        new Thread(new ClassC(1), "A").start();
//        new Thread(new ClassC(2), "B").start();
        new Thread(new NumberC(1, 65000), "A").start();
        new Thread(new NumberC(2, 65000), "B").start();
    }

    /** 两个线程访问同一个变量 dealCount，但是这里却没有线程安全问题(是有线程安全问题的，volatile的读操作和单写操作是线程安全的，但是dealCount++并不是原子性的)。值得深入探讨
     * 1. 起初，使用了synchronized 对dealCount的访问加了锁。毫无疑问，对dealCount的访问和修改是线程安全的。但是，
     * 代码运行的时间确是非常长的，每次线程通过竞争拿到对dealCount访问的锁之后，可能不满足处理条件，导致浪费了这次费力不少
     * 力气获得的锁（numberSize ）  -> 那么，可以借鉴双重检查锁的思想减少锁竞争，从而提高效率。注意这里的双重检查锁不用想单例模式里面要用volatile修饰（单例模式哪里可能会有null指针异常，这里不会）
     * @param numberSize
     */
    public static void towThreadsAlternate(int numberSize){

        new Thread(() -> {
            int count = 0;
            long wasteCpuCount = 0;
            while (count < numberSize) {
                synchronized (AlternateDeal.class) {
                    if (dealCount % 2 == 0) {
//                        log.info("threadA:{}", ++count);
                        dealCount++;
                        continue;
                    }
                    wasteCpuCount++;
                    log.debug("threadA waste cpu count:{}", ++wasteCpuCount);
                }
                count++;
            }
//            log.info("threadA waste count:{}", wasteCpuCount);
        }).start();

        new Thread(() -> {
            int count = 0;
            long wasteCpuCount = 0;
            while (count < numberSize){
                synchronized (AlternateDeal.class){
                    if (dealCount % 2 == 1){
//                        log.info("ThreadB:{}", (char) (++count + 64));
                        dealCount ++;
                        continue;
                    }
                    wasteCpuCount++;
                    log.debug("threadB waste cpu count:{}", ++wasteCpuCount);
                }
                count++;
            }
//            log.info("threadB waste count:{}", wasteCpuCount);
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
                synchronized (AlternateDeal.class){
                    if (dealCount % 2 == 0){
                        dealCount++;
                        count++;
                        log.debug("ThreadA:{}", count);
                        continue;
                    }
                    wasteCpuCount++;
                }
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
                synchronized (AlternateDeal.class){
                    if (dealCount % 2 == 1){
                        dealCount++;
                        count++;
                        log.debug("ThreadB:{}", (char)(count + 64));
                        continue;
                    }
                    wasteCpuCount++;
                }
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

    private static Lock printLock = new ReentrantLock();

    private static Condition danShuangCondition = printLock.newCondition();

    private static int count;

    private static class NumberA implements Runnable{

        @Override
        public void run() {

            printLock.lock();
            try {
                while (count < 26) {
                    while (count % 2 != 0) {
                        danShuangCondition.await();
                    }
                    if (count >= 26){
                        break;
                    }
                    log.info("\n ThreadA:{}", (char) (count + 65));
                    count++;
                    danShuangCondition.signalAll();
                }
            } catch (InterruptedException e) {
                log.error("exception", e);
            } finally {

                printLock.unlock();
            }
        }
    }

    //todo 可以，但是要注意一个问题。在外层while不满足之后，会进入await，当有其他线程signalAll之后，
    // 此时外层的条件不会再判断while，导致出现了count == 26 的情况出现，要在内层里面再判断count的值
    private static class NumberB implements Runnable{

        @Override
        public void run() {

            printLock.lock();
            try {
                while (count < 26) {
                    while (count % 2 == 0) {
                        danShuangCondition.await();
                    }
                    log.info("\n ThreadB:{}", (char) (count + 65));
                    count++;
                    danShuangCondition.signalAll();
                }

            }catch (Exception e){

                log.error("exception", e);
            }finally {
                printLock.unlock();
            }
        }
    }

    private static final AtomicInteger atomicInteger = new AtomicInteger(0);

    // 不行，因为没有保证处理的原子性。导致出现问题
    private static class ClassC implements Runnable{

        int type;

        int size = 0;

        public ClassC(int type){

            this.type = type;
        }

        @Override
        public void run() {

            while (size < 26) {
                if (type == 1) {

                    if (atomicInteger.get() % 2 == 0) {

                        int count = atomicInteger.get();
                        if (atomicInteger.compareAndSet(count, count + 1)) {
                            //双数
                            log.info(Thread.currentThread().getName() + ":\n{}", (char) (count + 65));
                            size++;
                            atomicInteger.compareAndSet(count + 1, count + 1);
                        }

                    }
                } else {
                    if (atomicInteger.get() % 2 != 0) {
                        int count = atomicInteger.get();
                        if (atomicInteger.compareAndSet(count, count + 1)) {
                            //单数
                            log.info(Thread.currentThread().getName() + ":\n{}", (char) (count + 65));
                            size++;
                            atomicInteger.compareAndSet(count + 1, count + 1);
                        }
                    }
                }
            }
        }
    }

    //通过synchronized 加 wait， notifyAll实现
    //注意 wait以及notify、notifyAll的调用发起者（必须持有发起对象的监视器）是被锁的对象（也就是当前线程获取的那个监视器，在当前线程发起这些操作。）
    //await的判断条件必须在循环里面，在被唤醒之后且通过了await的条件检查不用await一定注意是否要做再次判断（注意被唤醒之后且不用再进入await
    // 的代码是从wait之后执行的），就有可能跳过之前的条件检查
    private static class NumberC implements Runnable{

        int type = 0;
        int size = 0;
        static int currentNumber = 0;
        NumberC(int type, int size){

            this.type = type;
            this.size = size;
        }
        @SneakyThrows
        @Override
        public void run() {

            long startTime = System.currentTimeMillis();
            while (currentNumber < size) {
//                log.info(Thread.currentThread().getName());
                if (type == 1) {
//                    if (currentNumber % 2 == 0) {
                        synchronized (NumberC.class) {
                            //双重检查
                            Thread current = Thread.currentThread();
                            while (currentNumber % 2 != 0) {
//                                log.info(current.getName() + " waiting... currentNumber:{}", currentNumber);
                                NumberC.class.wait();
                            }
                            currentNumber++;
//                            log.info(current.getName() + ":\n {}", (char) (currentNumber + 65));
                            NumberC.class.notifyAll();
                        }
//                    }
                } else {
//                    if (currentNumber % 2 != 0) {
                        synchronized (NumberC.class) {
                            //双重检查
                            Thread current = Thread.currentThread();
                            while (currentNumber % 2 == 0) {
//                                log.info(current.getName() + " waiting... currentNumber:{}", currentNumber);
                                NumberC.class.wait();
                            }
                            currentNumber++;
//                            log.info(current.getName() + ":\n {}", (char) (currentNumber + 65));
                            NumberC.class.notifyAll();
                        }
//                    }
                }
            }
            log.info(Thread.currentThread() + " totalTime={}", System.currentTimeMillis() - startTime);
        }
    }
}
