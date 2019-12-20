package com.csii.ljj.thread13;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * thread-2 调用 m2 需要thread-1 调用完m1释放锁才能进行
 * 使用ReentrantLock ,与synchronized
 * 区别：使用ReentrantLock 必须手动释放锁,就算抛错也需要手动释放锁
 *
 */
public class ReentranLock2 {
    Lock lock = new ReentrantLock();

    void m1() {
        lock.lock();
        System.out.println(Thread.currentThread().getName() + "...");
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                System.out.println(i);
            }
            System.out.println("m1 ... end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

     void m2() {
        lock.lock();
        System.out.println("m2 .... end");
        lock.unlock();
    }

    public static void main(String[] args) {
        ReentranLock2 lock1 = new ReentranLock2();

        // thread-1 调用m1方法
        new Thread(() -> {
            lock1.m1();
        }, "thread-1").start();

        // thread-2 调用 m2方法，需等待thread-1结束才能调用
        new Thread(() -> {
            lock1.m2();
        }, "thread-2").start();

    }
}
