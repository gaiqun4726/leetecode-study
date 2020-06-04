package com.gaiqun;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * @author gaiqun
 * @date 2020/6/1
 */
public class CallbackStudy {

    private static ExecutorService executor = Executors.newFixedThreadPool(5);

    static class MyThread extends Thread {

        /**
         * 设置好回调函数以后，把任务提交给线程池，然后自己执行完。是异步非阻塞。
         */
        @Override
        public void run() {
            MyThread2 thread = new MyThread2(System.out::println);
            executor.submit(thread);
            System.out.println("MyThread finished.");
        }
    }

    static class MyThread2 extends Thread {

        private Consumer<String> consumer;

        MyThread2(Consumer<String> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            consumer.accept("executed");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
        try {
            Thread.sleep(1000);
            // 确认是第一个线程执行完成后，第二个线程才执行回调方法的。
            System.out.println(myThread.isAlive());
            // 要在任务执行完之后再关闭线程池
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 关闭线程池，主线程才会结束。
        executor.shutdown();
    }
}
