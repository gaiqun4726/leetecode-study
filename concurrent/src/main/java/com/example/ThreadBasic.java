package com.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gaiqun
 * @date 2020/4/3
 */
public class ThreadBasic {

    public static void main(String[] args) {
        // 1. interrupt可以中断阻塞的线程
        // 主线程会继续执行自己的任务，但最后会为子线程收尾，等待子线程结束
        MyThread myThread = new MyThread();
        myThread.start();
        myThread.interrupt(); // interrupt可以中断阻塞的线程
        System.out.println("Main finished.");

        // 2. 主线程会一直等待子线程的结束
        // 使用了线程池，主线程还是会等待线程池关闭以后才会结束
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new MyThread());
        executor.shutdown(); // 线程池用完需要关闭，否则主线程一致不会结束
        System.out.println("Main finished");

        // 3. interrupt不能中断running的线程
        MyThread2 myThread2 = new MyThread2();
        myThread2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myThread2.interrupt(); // interrupt不能中断running的线程
        System.out.println("Main finished.");
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                System.out.println("Thread finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThread2 extends Thread {

        @Override
        public void run() {
            while (true) {
                System.out.println("Thread is running.");
            }
            //System.out.println("Thread finished.");
        }
    }
}
