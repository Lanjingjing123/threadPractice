package com.csii.ljj.thread13;


/**
 * thread-2 调用 m2 需要thread-1 调用完m1释放锁才能进行
 * 使用 synchronized 进行实现
 */
public class ReentranLock1 {
    synchronized void m1(){
        System.out.println(Thread.currentThread().getName()+"...");
        for (int i=0 ; i<10 ; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }

        System.out.println("m1 ... end");

    }

    synchronized void m2(){
        System.out.println("m2 .... end");
    }

    public static void main(String[] args) {
        ReentranLock1 lock1 = new ReentranLock1();

        // thread-1 调用m1方法
        new Thread(()->{
            lock1.m1();
        },"thread-1").start();

        // thread-2 调用 m2方法，需等待thread-1结束才能调用
        new Thread(()->{
            lock1.m2();
        },"thread-2").start();

    }
}
