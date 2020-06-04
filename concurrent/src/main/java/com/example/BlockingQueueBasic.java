package com.example;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author gaiqun
 * @date 2020/5/28
 */
public class BlockingQueueBasic {

    private static final int FULL = 10;
    private static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(FULL);

    public static void main(String[] args) {
        new Consumer().start();
        new Producer().start();
    }

    static class Consumer extends Thread {

        @Override
        public void run() {
            while(true) {
                try {
                    sleep(3000);
                    System.out.println("consume : " + queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Producer extends Thread {

        @Override
        public void run() {
            int count = 0;
            while(true) {
                try {
                    queue.put(count);
                    System.out.println("produce : " + count++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
