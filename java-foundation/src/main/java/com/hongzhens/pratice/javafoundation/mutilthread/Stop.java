package com.hongzhens.pratice.javafoundation.mutilthread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: HongZhenSi
 * @date: 2020/11/24
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */

@Slf4j
public class Stop {


    public static void main(String[] args) {


//        new Thread(new A("A")).start();
//        new Thread(new A("B")).start();

        Thread b1 = new Thread(new B());
        b1.start();
        //主线程调用其他正在waiting（timed_waiting）的线程的interrupt方法
        try {
            Thread.sleep(5000L);
        }catch (Exception e){

        }
        b1.interrupt();

    }


    private static void innerMultiThread(){

        int count = 10;

        new Thread(new Runnable() {
            @Override
            public void run() {
                //这里对count 进行++ 是不对的，编译上并不允许  类似的还有函数式编程
//                count++;
            }
        }).start();
    }



    private static class B extends Thread{


        private static final ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {

            synchronized (B.class){
                try {

                    log.info("into B run.");
                    B.class.wait(1000 * 3);
                    log.info("B run. after wait.");

                }catch (InterruptedException e){

                    //发生 InterruptedException 之后会将中断标志位置为 false
                    log.error("B thread:{}, InterruptedException", this.isInterrupted(), e);
                }catch (Exception e){
                    log.error("B thread:{}, exception", this.isInterrupted(), e);
                }
            }
        }
    }

    private static class C extends Thread{

        private Thread thread;
        public C(){}
        public C(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            log.info("into C run");
            thread.interrupt();
        }
    }

    private static class A implements Runnable{

        private static final ReentrantLock lock = new ReentrantLock();
        // 一个lock可以有多个条件变量，每个条件变量有自己的等待集合（Synchronized只有一个隐式的条件变量，相对不够灵活）
        private static final Condition ageCondition = lock.newCondition();

        private String name;
        public A(){
            super();
        }

        public A(String name){
            super();
            this.name = name;
        }


        // 对于stop和interrupt方法，如果是使用的 ReentrantLock的话，都是不会自动释放的，
        // 必须在finally进行释放。Synchronized的话，是可以自动释放的
        @Override
        public void run() {
            try {
                lock.lock();
                try {
                    log.info("into thread:{}", name);
                    Thread.sleep(5000L);
                    log.info("thread:{} weak up", name);
//                    Thread.currentThread().stop();
                    Thread.currentThread().interrupt();
                } catch (Exception e) {

                    log.error("run exception", e);
                }
            }catch (Exception e){
                log.error("lock exception", e);
            }
            finally {

                log.info("into thread:{} finally. ", name);
                lock.unlock();
            }
        }

    }
}
