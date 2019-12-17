package com.csii.ljj.Thread7;

public class A {


    public synchronized void call(B b){
        System.out.println(Thread.currentThread().getName()+":"+"..A. start");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.call();
        System.out.println(Thread.currentThread().getName()+":"+"..A. end");
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+":"+"..A. start");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":"+"..A. end");
    }
}
