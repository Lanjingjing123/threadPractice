package com.csii.ljj.thread18;

import java.util.concurrent.Executor;

public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        new Thread(command).run();
//        command.run();
    }

    public static void main(String[] args) {
        new T01_MyExecutor().execute(()-> System.out.println(Thread.currentThread().getName()+" : hello Executor"));
    }
}
