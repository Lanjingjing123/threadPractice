package com.csii.ljj.thread16;

import java.util.ArrayList;
import java.util.List;

/**
 * 10000张票，10个线程购票，会产生多线程安全问题
 */
public class TicketSeller1 {
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
