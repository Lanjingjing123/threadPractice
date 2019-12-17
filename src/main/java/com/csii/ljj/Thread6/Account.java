package com.csii.ljj.Thread6;


/**
 * 脏读问题——写加锁，读未加锁
 * 解决：读写都加锁
 * set 里面调用 get （若都用synchronized修饰，则是可重入锁，通过这个可以知道synchronized是对象锁，
 *      一个线程在执行set时，若get也用synchronized修饰，另一个线程不能调用get方法）
 */
public class Account {
    String name;
    double balance;

    public synchronized  void set(String name,double balance){
        this.name = name;

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
        System.out.println(this.getBalance()+"----");
    }

    public /*synchronized */ double getBalance(){
        return this.balance;
    }

    public static void main(String[] args) {
        Account account = new Account();

        new Thread(()->account.set("zhangsan",100),"thread1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance());


    }

}
