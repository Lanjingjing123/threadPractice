package com.csii.ljj.thread10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 使用门栓
 * thread-2 开启门栓,
 * thread-1在容器容量达到5，开启门栓，thread-2直接能进，
 * 也不会影响thread-1 往下运行
 */
public class MyContainer5 {
    /*volatile*/ List list = new ArrayList();

    // 添加元素
    public void add(Object o) {
        list.add(o);
    }

    // 获得容器的大小
    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer5 container5 = new MyContainer5();

        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
            if (container5.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("thread-2 开始继续跑");
            }
            System.out.println("thread-2 结束" + (container5.size()));

        }, "thread-2").start();

        new Thread(() -> {
            // 获取 container5 堆内对象锁
            System.out.println("thread-1 启动");
            for (int i = 0; i < 10; i++) {

                container5.add(new Object());
                if (container5.size() == 5) {
                    // 通知 thread-2 进行唤醒
                    latch.countDown();
                }
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                System.out.println("add " + i);
            }

        }, "thread-1").start();


    }
}
