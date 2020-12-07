package com.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gaiqun
 * @date 2020/6/9
 */
public class ThreadPoolStudy {

    static Set<ExecutorService> set = new HashSet<>();
    public static void main(String[] args) {
        ThreadPoolStudy study = new ThreadPoolStudy();
        for(int i=0;i<10;i++) {
            study.execute();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for(ExecutorService e: set) {
            System.out.println(e.isShutdown());
        }
    }

    private void execute() {

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            //try {
            //    Thread.sleep(3000);
            //} catch (InterruptedException e) {
            //    e.printStackTrace();
            //}
            System.out.println("inside thread pool.");
        });
        set.add(executorService);
        System.out.println("outside thread pool.");
    }
}
