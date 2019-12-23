package com.csii.ljj.thread15;

import com.csii.ljj.Person;

/**
 * ThreadLocal是使用空间换时间——每个线程独自维护自己的变量，eg:这里的 tl, thread-2里进行set值，thread-1里面是不能进行get的
 * synchronized 是使用时间换空间——锁定一个对象，线程释放锁之后才能给另一个线程进行使用
 *
 * 使用 ThreadLocal 每个线程相互隔离
 * eg: thread-1 不能获取 thread-2 set的值
 * result:null
 */
public class ThreadLocal2 {
    static ThreadLocal<Person> tl = new ThreadLocal<Person>();

    public static void main(String[] args) {


        // thread-1 进行get 值
        new Thread(()->{

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());

        },"thread-1").start();


        // thread-2 进行 set值
        new Thread(()->{

            tl.set(new Person());
        },"thread-2").start();
    }
}
