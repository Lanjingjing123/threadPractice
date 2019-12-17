package com.csii.ljj.thread5;


/**
 * 非同步方法（无锁），多线程能直接访问
 * eg：m1是同步方法，线程需要等待，m2非同步方法，在m1执行过程中，也能执行T中的m2方法
 * 若在m2上面也加锁，则需要等m1执行结束才能执行m2
 */
public class T {

    public synchronized  void m1(){
        System.out.println(Thread.currentThread().getName()+":m1....start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+":m1 ... end");
    }

    public synchronized void m2(){
        System.out.println(Thread.currentThread().getName()+":m2.... start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+":m2...end");

    }

    public static void main(String[] args) {
        T t = new T();

//        new Thread(()->t.m1(),"t1").start();

        // 等价上面这个写法
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m1();
            }
        },"t1").start();

        new  Thread(()->t.m1(),"t3").start();
        new Thread(()->t.m2(),"t2").start();
        new Thread(()->t.m2(),"t4").start();
        System.out.println();
    }

}
