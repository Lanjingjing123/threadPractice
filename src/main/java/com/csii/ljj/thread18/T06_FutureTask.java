package com.csii.ljj.thread18;

import java.util.concurrent.*;

public class T06_FutureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });// new一个Callable内部类，重写call方法 new Callable (){ Int call()}

        new Thread(task).start();// 启动一个线程，执行任务
        System.out.println(task.get());// 阻塞，拿到结果才会往下走
        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit(()->{
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });

//        System.out.println(f.get());
        System.out.println(f.isDone());
        System.out.println(f.get());
//        System.out.println(f.isDone());
        service.shutdown();

    }
}
