package com.csii.demo;

/**
 * 三个窗口（线程）卖票，票总共200张
 * 要求：不能超卖
 *
 * @author lanjingjing
 * @date 2020/2/27
 */
public class SellTickets {
    // 初始化200张票
    private static int ticektNum = 2000;

    public static void main(String[] args) {
        SellTask task = new SellTask();
        new Thread(task, "thread-1").start();
        new Thread(task, "thread-2").start();
        new Thread(task, "thread-3").start();

    }

    static class SellTask implements Runnable {

        @Override
        public void run() {
            while (ticektNum > 0) {
                ticektNum--;
//                try {
                    System.out.println(Thread.currentThread().getName() + "卖票" + "还剩下：" + ticektNum);
//                    Thread.sleep(10);

//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }


        }
    }
}
