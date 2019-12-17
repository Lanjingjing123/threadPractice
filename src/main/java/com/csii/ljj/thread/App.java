package com.csii.ljj.thread;

/**
 * Hello world!
 *
 */
public class App {
    private String mm ;
    public static void main( String[] args ) {
        MyselfException myselfException = null;
        try{
            myselfException = new MyselfException();
            myselfException.testException();
        }catch (Exception e){
            System.out.println("调用出错");
            throw e;
    }

        System.out.println("go on");

    }



}
