package com.csii.ljj.thread14;

import java.util.LinkedList;

public class MyContainer1 {
    final private LinkedList<Object> lists = new LinkedList<Object>();
    final private int MAX_SIZE = 10;
    private int count = 0;

    /**
     * 生产者，生成达到10个，不在生产
     * @param o
     */
    public synchronized void put(Object o){
        while (lists.size() == MAX_SIZE){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread().getName()+" produce one ");
        lists.add(o);
        count ++;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notifyAll();// 不能使用notify,使用notify 有可能再通知一次生产者，就会造成死锁；
                        // 使用notifyAll 则可通知所有线程来争抢锁
    }


    /**
     * 消费者，生产数量为0，则进行等待生产者生产
     * @return
     */
    public synchronized Object get(){
        Object o = null;
        while (lists.size() == 0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        o = lists.removeFirst();
        count--;
        System.out.println(Thread.currentThread().getName()+" consume one");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.notifyAll();// 不能使用notify,使用notify 有可能再通知一次消费者，就会造成死锁；
                     // 使用notifyAll 则可通知所有线程来争抢锁
        return o;
    }

    public static void main(String[] args) {
        MyContainer1 container1 = new MyContainer1();
        // 消费者 10个
        for (int i=0;i<10;i++){
            new Thread(()->{
                for (int m=0;m<20;m++){
                    container1.get();
                }
            },"consumer-"+i).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 生产者 2个
        for (int j=0;j<2;j++){
            new Thread(()->{
                for (int a=0;a<100;a++){
                    container1.put(Thread.currentThread().getName()+" produce "+a);
                }
            },"prod-"+j).start();
        }
    }
}
