package com.csii.ljj.thread10;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：一个线程（thread-1）添加元素，另一个线程(thread-2)在容器存储达到5就结束该线程
 * list 使用volatile,则修改是读可见的，thread-2会停止
 */
public class MyContainer2 {
    volatile List list = new ArrayList();

    // 添加元素
    public void add(Object o){
        list.add(o);
    }

    // 获得容器的大小
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer2 container2 = new MyContainer2();


        new Thread(()->{
            System.out.println("thread-2 启动");
            while (true){
                if (container2.size()==5){
                    break;
                }
            }
            System.out.println("thread-2 结束");
        },"thread-2").start();

        new Thread(()->{
            System.out.println("thread-1 启动");
            for (int i=0 ; i<10; i++){
                container2.add(new Object());
                System.out.println("add "+i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"thread-1").start();


    }
}
