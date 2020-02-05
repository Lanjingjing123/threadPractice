package com.csii.ljj.thread18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 只有一个线程的线程池
 */
public class T09_SingleThreadPool {
    public static void main(String[] args) {
        ExecutorService service = Executors.newSingleThreadExecutor();

        for(int i=0;i<5;i++){
            final int j =i ;
            service.execute(()->{
                System.out.println(j + " " + Thread.currentThread().getName());
            });
        }
    }
}
