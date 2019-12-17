package com.csii.ljj.Thread7;

public class Threads implements Runnable {
    private Object a, b;

    public  Threads(Object a, Object b) {
        this.a = a;
        this.b = b;
    }


    @Override
    public void run() {
        synchronized (a) {
            System.out.println(Thread.currentThread().getName() +"获得锁"+ " lock:" + a);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println(Thread.currentThread().getName() + " lock:" + b);
            }
        }
    }
}