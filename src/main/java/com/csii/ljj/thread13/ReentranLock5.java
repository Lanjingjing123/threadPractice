package com.csii.ljj.thread13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 比较公平锁和非公平锁
 */
public class ReentranLock5 extends Thread {
    public static ReentrantLock lock = new ReentrantLock(true);// true 指定公平锁

    public void run() {
        for (int i = 0; i < 100; i++) {

                lock.lock();// 获得锁
            try {
                System.out.println(Thread.currentThread().getName() + "：获得锁");
                try {
                    Thread.sleep(1); // 增加等待时间，更好的体现公平原则，若不增加等待时间，有可能出现 1212 11112 12 这样的情况发生
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();// 释放锁
            }

        }
    }

    public static void main(String[] args) {
        ReentranLock5 r1 = new ReentranLock5();

        Thread thread1 = new Thread(r1);
        Thread thread2 = new Thread(r1);
        thread1.start();
        thread2.start();
    }

}
