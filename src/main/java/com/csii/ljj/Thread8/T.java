package com.csii.ljj.Thread8;

/**
 * volatile 可设置线程可见性，若无这个关键字，变量更改线程不可见
 */
public class T {
    /*volatile*/ boolean running = true;
    synchronized void  m(){
        System.out.println(Thread.currentThread().getName()+":"+"获得锁"+"... begin");
        while(running){

            // 若加了等待时间，也会停止;视频解释：CPU在闲的时候会进主内存进行读取
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("-----end--------------");
    }


    public static void main(String[] args) {
        T t = new T();
        new Thread(()->t.m(),"thread1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->t.m(),"thread2").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;
    }
}
