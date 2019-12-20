package com.csii.ljj.thread10;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：一个线程（thread-1）添加元素，另一个线程(thread-2)在容器存储达到5就结束该线程
 * list 使用volatile,则修改是读可见的，thread-2会停止
 * 问题：thread-2 对CPU占用有消耗，去掉while，当list.size = 800 时通知该线程进行结束，其他时间进行休眠
 * 使用 wait(),wait会释放锁，和notify(),notify 不会释放锁进行等待和通知
 * e:
 * thread-2 启动
 * thread-2:0
 * thread-1 启动
 * add 0
 * add 1
 * add 2
 * add 3
 * add 4
 * add 5
 * add 6
 * add 7
 * add 8
 * add 9
 * thread-2 开始继续跑
 * thread-2 结束10
 *
 *
 * 思考：为什么thread-1结束之后，thread-2才继续进行？
 * 解释：container4 这个对象，thread-1,thread-2都需要申请到这个对象才能进行自己线程里面的逻辑
 * 在thread-2 使用wait()，之后，会释放 container4 这个对象锁，thread-1拿到之后进行add 方法，当size=5之后
 * thread-1使用notify()唤醒 thread-2,但是 thread-2要执行下去也需要  container4 对象锁，此时thread-1 还在占用，没办法申请
 * 因此只能等 thread-1 释放了锁，thread-2才能获取锁，然后运行
 *
 * 解决上述问题：
 *   通知唤醒 thread-1 后，thread-2进行线程等待，thread-1 被唤醒之后,在通知唤醒 thread-1 ,
 *   就可以达到如下效果，可运行 MyContainer3，MyContainer4做比较
 *
 *  thread-2 启动
 *  thread-2:0
 *  thread-1 启动
 *  add 0
 *  add 1
 *  add 2
 *  add 3
 *  thread-2 开始继续跑
 *  thread-2 结束5
 *  add 4
 *  add 5
 *  add 6
 *  add 7
 *  add 8
 *  add 9
 */
public class MyContainer4 {
    /*volatile*/ List list = new ArrayList();

    // 添加元素
    public void add(Object o){
        list.add(o);
    }

    // 获得容器的大小
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer4 container4 = new MyContainer4();


        new Thread(()->{
            // 获取 container4 堆内对象锁
            synchronized (container4){
                System.out.println("thread-2 启动");
                if (container4.size()!=5){
                    try {
                        System.out.println("thread-2:"+container4.size());
                        container4.wait();// 使用notify()之后，此线程重新申请获得container4这个对象锁，进行下面的代码
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread-2 开始继续跑");
                }
                // 唤醒线程 thread-1
                container4.notify();
                System.out.println("thread-2 结束"+(container4.size()));
            }

        },"thread-2").start();

        new Thread(()->{
            // 获取 container4 堆内对象锁
            synchronized (container4){
                System.out.println("thread-1 启动");
                for (int i=0 ; i<10; i++){

                    container4.add(new Object());
                    if (container4.size()==5){
                        // 通知 thread-2 进行唤醒
                        container4.notify();
                        try {
                            // 当前线程进行等待
                            container4.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }


                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("add "+i);
                }
            }

        },"thread-1").start();


    }
}
