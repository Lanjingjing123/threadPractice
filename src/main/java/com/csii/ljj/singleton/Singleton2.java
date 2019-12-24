package com.csii.ljj.singleton;

/**
 * 懒汉式,需要在实例方法进行加锁（比较重）
 */
public class Singleton2 {
    private static Singleton2 o = null;
    private Singleton2(){}

    // 不加 synchronized 会出现多线程不安全问题，会产生多个实例，而不是单例
    public synchronized static Singleton2 get(){
        if (o == null){

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            o = new Singleton2();
        }
        return o;
    }



    public  static void main(String[] args) {

        Singleton2 singleton2 =new Singleton2();
        for (int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+":"+singleton2.get());
            },"thread-"+i).start();
        }
    }
}
