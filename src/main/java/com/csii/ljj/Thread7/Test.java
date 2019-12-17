package com.csii.ljj.Thread7;



public class Test {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        Threads t1 = new Threads(o1, o2);
        Threads t2 = new Threads(o2, o1);
        new Thread(t1).start();
        new Thread(t2).start();
    }
}

