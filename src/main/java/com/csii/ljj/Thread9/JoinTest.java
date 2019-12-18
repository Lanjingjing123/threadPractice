package com.csii.ljj.Thread9;
public class JoinTest {
    public static void main(String [] args) throws InterruptedException {
        ThreadJoinTest t1 = new ThreadJoinTest("小明");
        ThreadJoinTest t2 = new ThreadJoinTest("小东");
        t1.start();
        /**join方法可以传递参数，join(10)表示main线程会等待t1线程10毫秒，10毫秒过去后，
         * main线程和t1线程之间执行顺序由串行执行变为普通的并行执行
         * 不加参数，则需要等待上面线程执行结束才能执行下面
         */


        t2.start();
        t1.join();
        System.out.println("=========1231231=============");

    }

}
class ThreadJoinTest extends Thread{
    public ThreadJoinTest(String name){
        super(name);
    }
    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            System.out.println(this.getName() + ":" + i);
        }
    }
}
