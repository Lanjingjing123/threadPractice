package com.csii.ljj.thread13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock.tryLock(),可以设置尝试获取锁，并进行逻辑操作；不会一直等待
 * 而 syncronied 会一直等待释放锁
 */
public class ReentranLock3 {
    Lock lock = new ReentrantLock();

    void m1() {

        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "... start");

            for (int i = 0; i < 10; i++) {

                Thread.sleep(1000);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    
    
    void  m2(){
        boolean b = true;
        try {
             b = lock.tryLock(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m2 end..."+b);
    }

    public static void main(String[] args) {
        ReentranLock3 lock3 = new ReentranLock3();

        // thread-1 得到锁执行m1方法
        new Thread(lock3::m1,"thread-1").start();

        // thrad-2 执行m2方法，先尝试获取对象锁，等待5s获取不到也进行执行
        new Thread(lock3::m2,"thread-2").start();

    }
}
