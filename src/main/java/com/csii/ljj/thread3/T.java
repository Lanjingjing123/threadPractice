package com.csii.ljj.thread3;

/**
 * synchronized 锁定的是一个对象不是一个代码块
 */
public class T {
    private int count = 10;

    public void m(){
        synchronized (this){
            count--;
            System.out.println(Thread.currentThread().getName()+"count = "+ count);

        }
    }
}
