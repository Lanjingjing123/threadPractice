package com.csii.ljj.singleton;

/**
 * 线程安全的单例模式
 * Singleton1 - 饿汉式单例模式，在 Singleton1 这个对象被 new 出来便实例化
 * 问题：不能进行懒加载，应该是在需要的时候才 实例化
 */
public class Singleton1 {
   private static Singleton1 o = new Singleton1();

   private Singleton1(){

   }

   public static Singleton1 get(){
       return o;
   }

    public static void main(String[] args) {

        Singleton1 singleton1 =new Singleton1();
        for (int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+":"+singleton1.get());
            },"thread-"+i).start();
        }
    }
}
