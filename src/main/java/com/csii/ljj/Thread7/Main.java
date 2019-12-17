package com.csii.ljj.Thread7;

class lock1 implements Runnable{
    public void run(){
        synchronized(Main.l1){
            System.out.println("1：我获得了第一个锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(Main.l2){
                System.out.println("1：我获得了第二个锁");
            }
        }
    }
}

class lock2 implements Runnable{
    public void run(){
        synchronized(Main.l2){
            System.out.println("2：我获得了第一个锁");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized(Main.l1){
                System.out.println("2：我获得了第二个锁");
            }
        }
    }
}

public class Main {
    public static String l1="lock1";
    public static String l2="lock2";

    public static void main (String[] args) {
        Thread lo1=new Thread(new lock1());
        Thread lo2=new Thread(new lock2());
        lo1.start();
        lo2.start();

    }

}
