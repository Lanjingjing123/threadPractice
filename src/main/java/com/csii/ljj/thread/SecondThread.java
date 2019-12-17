package com.csii.ljj.thread;

public class SecondThread implements Runnable {
    // 共享资源变量
        private int i;
    @Override
    public  synchronized void  run() {
        //当线程类实现Runnable接口时，
        //如果想获取当前线程，只能用Thread.currentThread()方法。
        // 每个线程遍历10次
        for (int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName() +"开始执行业务:"+i);
        }
    }

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("====================BEGIN====================");
        System.out.println("========================================");
        for (int i = 0; i < 10000; i++) {//
            // 启动10个线程
            if (i % 1000 ==0){//每个借据号,使用一个线程进行业务处理
                SecondThread st = new SecondThread();
                //通过new Thread(target , name)方法创建新线程
                String name = String.format("thread-%d",i/1000);
                Thread thread = new Thread(st , name);
                thread.start();
            }
        }

        System.out.println("========================================");
        System.out.println("====================END====================");
        System.out.println("========================================");
    }

}
