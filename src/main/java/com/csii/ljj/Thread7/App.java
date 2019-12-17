package com.csii.ljj.Thread7;

public class App {
    public static void main(String[] args) {
        A a = new A();
        B b = new B();

        new Thread(()->a.call(),"Thread-1").start();
        new Thread(()->b.call(),"Thread-2").start();

        System.out.println("=========END=====");
    }
}
