package com.csii.ljj.thread17;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 写式复制
 * 特点：读很快，写需要copy一份才能复制
 */
public class T02_CopyOnWriteList {
    public static void main(String[] args) {
        List<String> list =
//                new ArrayList<>(); // ArrayList 线程不安全，方法未加锁，里面的操作可能被打断
//                  new Vector<>();
                  new CopyOnWriteArrayList<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];

        for (int i=0; i<ths.length;i++){
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int i=0 ; i<1000 ; i++){
                        list.add("a"+r.nextInt(100000));
                    }
                }
            };

            ths[i] = new Thread(task);

        }

        runAndComputeTime(ths);
        System.out.println(list.size());

    }

    static void runAndComputeTime(Thread[] ths){
        long s1 = System.currentTimeMillis();
        Arrays.asList(ths).forEach(t->t.start());
        Arrays.asList(ths).forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long s2 = System.currentTimeMillis();
        System.out.println(s2-s1);
    }
}
