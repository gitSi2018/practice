package com.hongzhens.pratice.javafoundation.collect;

import com.hongzhens.pratice.common.utils.MRandomUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: HongZhenSi
 * @date: 2020/12/10
 * @modifiedBy:
 * @description:
 * @version: 1.0
 */
@Slf4j
public class MBlockQueue<T> {


    private MQueue<T> head;

    private MQueue<T> tail;

    private int maxSize = 1 << 4;

    private int size = 0;

    public MBlockQueue(){

    }

    public MBlockQueue(int maxSize){

        this.maxSize = maxSize;
    }

    private final Lock lock = new ReentrantLock();

    private static class MQueue<T>{

        private MQueue<T> next;

        private T data;
    }

    public int size(){

        return size;
    }


    private final Condition notFullCondition = lock.newCondition();
    public void producer(T data) throws InterruptedException{

        lock.lock();
        try {
            while (size >= maxSize) {

                log.warn("MBlockQueue producer, queue is full. will await. size:{}", size);
                notFullCondition.await();
            }
            MQueue<T> mQueue = new MQueue<T>();
            mQueue.data = data;
            if (head == null){
                head = mQueue;
                tail = mQueue;
            }else {
                tail.next = mQueue;
                tail = mQueue;
            }
            size++;
            notEmptyCondition.signalAll();
        }finally {
            lock.unlock();
        }
    }

    private final Condition notEmptyCondition = lock.newCondition();
    public T consumer() throws InterruptedException {

        lock.lock();
        try {
            while (size == 0){

                log.warn("MBlockQueue consumer, queue is empty. will await. size:{}", size);
                notEmptyCondition.await();
            }
            MQueue<T> mQueue = head;
            head = head.next;
            if (head == null){
                tail = null;
            }
            size--;
            notFullCondition.signalAll();
            return mQueue.data;
        }
        finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        MBlockQueue<String> mBlockQueue = new MBlockQueue<>(10);
        new Thread(() -> dealProducer(mBlockQueue), "producerThread").start();
        new Thread(() -> dealConsumer(mBlockQueue), "consumerThread").start();
    }

    public static void dealProducer(MBlockQueue<String> mBlockQueue){

        int index = 0;
        while (true){
            int second = MRandomUtils.random(1, 0) ;
            try {
                log.info("into dealProducer");
                index++;
                mBlockQueue.producer("第:" + index + "个" + "，至少等" + second + "s再生产");
                int size =mBlockQueue.size();
                log.info("added 第:{} 个. size:{}, will sleep:{}", index, size, second);
                Thread.sleep(second * 1000);
            }catch (InterruptedException e){
                log.error("dealProducer failed. ", e);
            }
        }
    }

    public static void dealConsumer(MBlockQueue<String> mBlockQueue){

        while (true){

            int second = MRandomUtils.random(1, 20) ;
            try {

                log.info("into dealConsumer");
                String data = mBlockQueue.consumer();
                int size =mBlockQueue.size();
                log.info("removed. size:{}, data:{}, will sleep:{} s", size, data, second);
                Thread.sleep(second * 1000);

            }catch (InterruptedException e){
                log.error("dealConsumer failed. ", e);
            }
        }
    }

}
