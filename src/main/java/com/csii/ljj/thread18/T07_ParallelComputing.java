package com.csii.ljj.thread18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 线程池可以用来并行计算
 */
public class T07_ParallelComputing {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        List<Integer> results = getPrime(1,2000000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        final int cpuCoreNum =9;
        ExecutorService service = Executors.newFixedThreadPool(cpuCoreNum);
        // 分发10个任务
        MyTask task1 = new MyTask(1,400000);
        MyTask task2 = new MyTask(400001,600000);
        MyTask task3 = new MyTask(600001,800000);
        MyTask task4 = new MyTask(800001,1000000);
        MyTask task5 = new MyTask(1000001,1200000);
        MyTask task6 = new MyTask(1200001,1400000);
        MyTask task7 = new MyTask(1400001,1600000);
        MyTask task8 = new MyTask(1600001,1800000);
        MyTask task9 = new MyTask(1800001,1900000);
        MyTask task10 = new MyTask(1900001,2000000);
        // 将10个任务提交给线程池
        Future<List<Integer>> f1 = service.submit(task1);
        Future<List<Integer>> f2 = service.submit(task2);
        Future<List<Integer>> f3 = service.submit(task3);
        Future<List<Integer>> f4 = service.submit(task4);
        Future<List<Integer>> f5 = service.submit(task5);
        Future<List<Integer>> f6 = service.submit(task6);
        Future<List<Integer>> f7 = service.submit(task7);
        Future<List<Integer>> f8 = service.submit(task8);
        Future<List<Integer>> f9 = service.submit(task9);
        Future<List<Integer>> f10 = service.submit(task10);
        start = System.currentTimeMillis();

        f1.get();// 阻塞
        f2.get();
        f3.get();
        f4.get();
        f5.get();
        f6.get();
        f7.get();
        f8.get();
        f9.get();
        f10.get();

        end = System.currentTimeMillis();
        System.out.println(end - start);

        System.out.println(service);
        service.shutdown();


    }


    static class MyTask implements Callable<List<Integer>> {
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }


    }

    static boolean isPrime(int num) {
        for (int i = 2; i < num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            if (isPrime(i))
                results.add(i);
        }
        return results;
    }
}
