package com.csii.ljj.thread11;


/**
 * eg: 抛异常，释放锁
 */
public class T {
    int count ;
    synchronized void m(){
        System.out.println(Thread.currentThread().getName()+"... start");
        while (true){

            count++;
            System.out.println("");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (count==10){
                int i = 1/0;
            }


        }
    }

    public static void main(String[] args) {
            T t = new T();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        },"thread-1").start();
    }
}
