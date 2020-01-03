package com.csii.ljj.thread17;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

public class T01_ConcurrentMap {
    public static void main(String[] args) {

        // ConcurrentHashMap 比 Hashtable粒度要细
//    Map<String,String> map = new ConcurrentHashMap<>();
//        Map<String,String> map = new ConcurrentSkipListMap<>();
//        Map<String,String> map = new ConcurrentSkipListMap<>();
        Map<String,String> map = new Hashtable<>();
//        Map<String,String> map = new HashMap<>();
        Random r = new Random();
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length);
        Long startTime = System.currentTimeMillis();
        for (int i=0;i<ths.length;i++){
            ths[i] = new Thread(()->{
                for (int j=0;j<10000;j++){
                    map.put("a"+r.nextInt(100000),"a"+r.nextInt(100000));
                }
                latch.countDown();

            },"thread-"+i);
        }

        Arrays.asList(ths).forEach(t->t.start());

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
    }

}
