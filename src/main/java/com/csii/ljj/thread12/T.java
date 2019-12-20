package com.csii.ljj.thread12;

/**
 * 验证 对象锁是锁在堆上，不是在栈上
 */
public class T {
    Object o = new Object();

    void m(){
        synchronized (o){
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            System.out.println(Thread.currentThread().getName()+"...");

            }
        }
    }

    public static void main(String[] args) {
        T t = new T();
        /**
         * 第一种 ========>begin
         */
        // thread-1 申请T对象锁，thread-2 不能使用
        new Thread(()->{
            t.m();},"thread-1").start();

        // thread-2
//            new Thread(()->{
//                t.m();
//        },"thread-2").start();

        /**
         * 第一种 ========> end
         */

        /**
         * 第二种 ========>begin
         */

        System.out.println("=============================");
//        t.o = new Object();
        // thread-3
        new Thread(()->{
            t.m();
        },"thread-3").start();

    }
}
