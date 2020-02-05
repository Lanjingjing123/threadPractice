package com.csii.ljj.thread18;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 精灵线程
 */
public class T11_WorkStealingPool {
    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(12);// daemon 线程
        System.out.println(Runtime.getRuntime().availableProcessors());

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(3000));
        service.execute(new R(4000));
        service.execute(new R(5000));
        service.execute(new R(6000));
        service.execute(new R(7000));
        service.execute(new R(8000));
        service.execute(new R(9000));
        service.execute(new R(10000));
        service.execute(new R(11000));
        service.execute(new R(12000));
        service.execute(new R(2000));
        System.in.read();

    }

    static class R implements Runnable{
        int time ;
        R(int t){
            this.time = t;
        }
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time+" "+ Thread.currentThread().getName());
        }
    }
}
