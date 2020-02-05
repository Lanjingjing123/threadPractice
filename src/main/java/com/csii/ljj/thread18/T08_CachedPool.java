package com.csii.ljj.thread18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class T08_CachedPool {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);
        for (int i=0 ;i<2 ;i++){
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        // 等待2000ms，会对上面的线程池进行复用
        TimeUnit.MILLISECONDS.sleep(2000);

        for (int i=0;i<5;i++){
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        TimeUnit.SECONDS.sleep(80);
        // 线程池里面的线程在经历过60s空闲之后会关闭
        System.out.println(service);

    }
}
