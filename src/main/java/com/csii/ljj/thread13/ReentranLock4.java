package com.csii.ljj.thread13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock.lockInterruptibly 与  thread2.interrupt() 一个作为通知一个作为响应
 * lock.lockInterruptibly 响应thread2.interrupt()的通知进行阻断线程，会抛 InterruptedException 异常进行打断
 * finally 应该判断是否获得了锁进行锁的释放
 */
public class ReentranLock4 {
    Lock lock = new ReentrantLock();

    void m1() {

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "... start");
            TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            System.out.println("interrupted!");
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    void m2() {
        boolean b = false;
        try {
            b = lock.tryLock(5, TimeUnit.SECONDS);
            System.out.println(b);


            lock.lockInterruptibly();//响应thread-2的打断操作,这个执行会直接进catch块儿，下面的代码不会执行
            System.out.println("打断结束===");



        } catch (InterruptedException e) {
            System.out.println("============thread-2 interrupted=========");
//            e.printStackTrace();
        } finally {
            if (b) {
                lock.unlock();

            }else{
                System.out.println("未获得锁");
            }
        }
        System.out.println("m2 end..." + b);
    }

    public static void main(String[] args) {
        ReentranLock4 lock3 = new ReentranLock4();

        // thread-1 得到锁执行m1方法
        new Thread(lock3::m1, "thread-1").start();

        // thrad-2 执行m2方法，先尝试获取对象锁，等待5s获取不到也进行执行
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock3.m2();
            }
        },"thread-2");

        thread2.start();

        try {
            //
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();

    }
}
