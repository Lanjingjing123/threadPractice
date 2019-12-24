package com.csii.ljj.singleton;

/**
 * 懒汉式,需要在实例方法实例里面进行加锁，满足懒加载-需要一个实例对象的时候在new
 *
 *  使用：Double Check Locking 双检查锁机制（推荐）
 *
 *  注：静态方法锁的是对 Class 对象加锁——Class对象：每个类的唯一类对象
 *      非静态方法是对 this 对象加锁—— this对象：指代当前使用对象
 */
public class Singleton3 {
    private static Singleton3 o = null;
    private Singleton3(){}

    // 不加 synchronized 会出现多线程不安全问题，会产生多个实例，而不是单例
    public synchronized static Singleton3 get(){
        if (o == null){
            synchronized (Singleton3.class){ // 静态方法,Class对象加锁
                if ( o == null){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    o = new Singleton3();
                }
            }

        }
        return o;
    }



    public  static void main(String[] args) {

        Singleton3 singleton3 =new Singleton3();
        for (int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+":"+singleton3.get());
            },"thread-"+i).start();
        }
    }
}
