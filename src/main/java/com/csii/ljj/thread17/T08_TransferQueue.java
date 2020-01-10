package com.csii.ljj.thread17;

import java.util.concurrent.LinkedTransferQueue;


/**
 * TransferQueue 是直接与消费者线程相连，需要先启动消费者，
 * 问题：若后启动消费者，会阻塞
 */
public class T08_TransferQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

//        new Thread(()->{
//            try {
//                System.out.println(strs.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        },"consumer-thread").start();

        // transfer() 方法会阻塞
        strs.transfer("aaaa");
        // put() 方法非阻塞
//        strs.put("aaa");
        new Thread(()->{
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consumer-thread").start();
    }
}
