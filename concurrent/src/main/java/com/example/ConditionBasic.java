package com.example;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author gaiqun
 * @date 2020/5/28
 */
public class ConditionBasic {
    private static final int FULL = 10;
    private static Queue<Integer> queue = new PriorityQueue<>(FULL);
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition fullCondition = lock.newCondition();
    private static Condition emptyCondition = lock.newCondition();


    public static void main(String[] args) {
        new Consumer().start();
        new Producer().start();
    }

    static class Consumer extends Thread {

        @Override
        public void run() {
            while(true) {
                lock.lock();
                try {
                    sleep(1000);
                    if(queue.isEmpty()) {
                        fullCondition.await();
                    }
                    System.out.println("consume: " + queue.poll());
                    emptyCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    static class Producer extends Thread {

        @Override
        public void run() {
            int count = 0;
            while(true) {
                lock.lock();
                try {
                    if(queue.size()==FULL) {
                        emptyCondition.await();
                    }
                    queue.offer(count);
                    System.out.println("produce : " + count++);
                    fullCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
