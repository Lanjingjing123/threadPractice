package com.csii.ljj.thread;

public class Deom_1 extends Thread {

    public void run(){
        super.run();
        System.out.println("MyThread01");
    }

    public static void main(String[] args){
        Deom_1 demo=new Deom_1();
        demo.setName("Demo_1");
        demo.start();
        System.out.println("当前线程的名字:"+Thread.currentThread().getName()+" 运行结束");
    }
}
