package com.csii.ljj.thread17;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class T06_ArrayBlockingQueue {
    static BlockingQueue<String> strs = new ArrayBlockingQueue<>(10);

    static Random r = new Random();

    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<10;i++){
            strs.put("a"+i);
        }

//        strs.put("saaa");
//        strs.add("saaa");
//        strs.offer("saaa");
        strs.offer("aaa",2, TimeUnit.SECONDS);
        System.out.println(strs);
    }
}
