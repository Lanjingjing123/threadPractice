package com.csii.ljj.thread16;

import java.util.ArrayList;
import java.util.List;

/**
 * 10000张票，10个线程购票，
 * 对 list 进行加锁，操作和判断放在加锁的代码里面，线程安全，
 * 问题：效率低
 */
public class TicketSeller3 {
    static List<String> list = new ArrayList<String>();

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
                    synchronized (list){
                        if (list.size()!=0){
                            System.out.println("销售了"+list.remove(0));
                        }else{
                            break;
                        }
                    }

                }
            },"Thread-"+i).start();
        }
    }

}
