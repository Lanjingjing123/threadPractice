package com.csii.ljj.thread17;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 0容量队列
 */
public class T09_SyscronizeQueue {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strs = new SynchronousQueue<>();

        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread-1").start();

//        strs.add("aaa");
        strs.put("aaa");// put方法会阻塞
        System.out.println(strs.size());
    }
}
