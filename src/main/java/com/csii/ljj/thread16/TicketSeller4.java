package com.csii.ljj.thread16;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;


/**
 * 使用并发容器 ConcurrentLinkedDeque
 */
public class TicketSeller4 {

    static Queue<String> list = new ConcurrentLinkedDeque<String>();
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
//                    try {
//                        Thread.sleep(10);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                    // 这里先执行操作，再进行判断
                    String s = list.poll();
                    if (s==null){
                        break;
                    } else{
                        System.out.println(Thread.currentThread().getName()+" 销售了--"+s);
                    }

                }
            },"Thread-"+i).start();
        }
    }

}
