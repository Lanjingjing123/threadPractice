package com.csii.ljj.thread16;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Vector 的线程操作是原子性的：add方法 是原子性的
 * 10000张票，10个线程购票，会产生多线程安全问题
 * 问题：使用 Vector 也一样 会导致线程安全问题
 */
public class TicketSeller2 {
    static Vector<String> list = new Vector<>();

    static {
        for (int i=1;i<=10000;i++){
            list.add("票编号-"+i);
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            new Thread(()->{

                while (true){
                    // 判断跟操作直接可能会被打断，造成线程安全问题
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (list.size()!=0){

                        System.out.println("销售了"+list.remove(0));
                    }else{
                        break;
                    }
                }
            },"Thread-"+i).start();
        }
    }

}
