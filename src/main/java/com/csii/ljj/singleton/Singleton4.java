package com.csii.ljj.singleton;

/**
 * 使用静态内部类实现，无需加锁，(多线程安全)
 * 问题：遇见序列化和反序列化对象，线程不安全会有多个实例
 */
public class Singleton4 {

    private Singleton4(){}

    private static class Inner{
        private static Singleton4 singleton4 = new Singleton4();
    }

    public Singleton4 get(){
        return Inner.singleton4;
    }

    public static void main(String[] args) {
        Singleton4 singleton4 =new Singleton4();
        for (int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+":"+singleton4.get());
            },"thread-"+i).start();
        }
    }
}
