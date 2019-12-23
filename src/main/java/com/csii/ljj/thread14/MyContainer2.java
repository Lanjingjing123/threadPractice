package com.csii.ljj.thread14;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyContainer2 {
    final private LinkedList<Object> list = new LinkedList<>();
    final private int MAX_SIZE = 10;
    private int count = 0;

    //
    private Lock lock = new ReentrantLock();

    private Condition consumer = lock.newCondition();
    private Condition producer = lock.newCondition();

    public void put(Object o) {
        try {
            lock.lock();
            while (list.size() == MAX_SIZE) {
                producer.await();//
            }

            // 正常添加
            list.add(o);
            count++;
            consumer.signalAll();// 唤醒所有消费者

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public Object get(){
        Object o = null;
        try{
            lock.lock();
            while (list.size() == 0){
                consumer.await();
            }
            // 正常消费
            o  = list.removeFirst();
            count --;
            // 通知消费者生产
            producer.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return o;
    }


    public static void main(String[] args) {
        MyContainer2 container2 = new MyContainer2();
        // 消费者
        for (int i=0;i<10;i++){
            new Thread(()->{
                for (int j=0;j<20;j++){
                    System.out.println(Thread.currentThread().getName()+" consumer "+container2.get()+" "+j);
                }
            },"c-"+i).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 生产者
        for (int i=0;i<2;i++){
            new Thread(()->{
                for (int j=0;j<100;j++){
                     container2.put(Thread.currentThread().getName()+"-"+j);
                }
            },"p"+i).start();
        }


    }
}
