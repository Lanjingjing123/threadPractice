package com.csii.ljj.thread15;

import com.csii.ljj.Person;

/**
 * 一个线程的变量更改让另一个线程知道:使用 volatile
 */
public class ThreadLocal1 {
    volatile static Person p = new Person();

    public static void main(String[] args) {

        // thread-1
        new Thread(()->{
           while (true){
               try {
                   Thread.sleep(100);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println(p.name);
           }


        },"thread-1").start();


        // thread-2 更改变量
        new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        },"thread-2").start();
    }


}
