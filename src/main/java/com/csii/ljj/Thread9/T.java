package com.csii.ljj.Thread9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile 读可见性，无原子性，count++ 操作是非原子性的，两步操作 ,count=count+1:第一步：count+1,第二步：count = (count+1)
 * eg: count =100
 * main-thread: ount =100
 * thread-1:count=100;thread-2:count=100,thread-3:count=100
 *
 * thread-1：count++ => main-thread:count=101
 * thread-2:count++ : 仍使用 100+1=101,push到主线程, 101,这样 应该加2,102 push到 main-thread ,实际只加1 push 101到main-thread,少加了
 */
public class T {
//    /*volatile */int count =0;

    /**
     * Atomic* 原子类，可保证原子性
     */
    AtomicInteger count = new AtomicInteger(0);
    /**
     * volatile 只保证可见性，synchronized既保证可见性，也保证原子性
     *
     */
    /*synchronized*/ void m(){
        for (int i=0;i<1000000;i++){
//            count++;

            // 不加synchronized,可能造成 thread-1，count=999 进入判断条件，然后进行+1=1000,
            // 此时第二个线程也进入if里面,此时1000+1=10001
//            if (count.get()<1000){
                count.incrementAndGet();
//            }

        }
    }

    public static void main(String[] args) {
        T t = new T();
        List<Thread> threads = new ArrayList<Thread>();
        for (int i=0;i<10;i++){
            threads.add(new Thread(t::m,"thread-"+i));
        }
        long begin = System.currentTimeMillis();

        threads.forEach((o)->o.start());
        threads.forEach((o)->{
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.count);
        long end = System.currentTimeMillis();
        System.out.println(end-begin);
    }
}

