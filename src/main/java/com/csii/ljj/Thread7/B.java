package com.csii.ljj.Thread7;

public class B {

    public synchronized void call(A a){
        System.out.println(Thread.currentThread().getName()+":"+"..B. start");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":"+"..B. end");
    }

    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+":"+"..B. start");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+":"+"..B. end");
    }
}
