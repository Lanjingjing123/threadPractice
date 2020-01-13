package com.csii.ljj;
import java.util.concurrent.CountDownLatch;
import java.util.Scanner;
public class Assignment extends Thread {
    private int A[][];
    private int B[][];
    private int index;
    private int gap;
    private int result[][];
    private CountDownLatch countDownLatch;


    public Assignment(int A[][], int B[][], int index, int gap, int result[][], CountDownLatch countDownLatch) {
        this.A = A;
        this.B = B;
        this.index = index;
        this.gap = gap;
        this.result = result;
        this.countDownLatch = countDownLatch;
    }

    // 计算特定范围内的结果
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // TODO Auto-generated method stub
        for (int i = index * gap; i < (index + 1) * gap; i++)
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < B.length; k++)
                    result[i][j] += A[i][k] * B[k][j];
            }
        // 线程数减1
        countDownLatch.countDown();
    }
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入矩阵的行列数");
        int l = scan.nextInt();
        // 声明和初始化
        long startTime;
        long finalTime;
        int A[][] = new int[l][l];
        int B[][] = new int[l][l];
        //存放并行计算结果
        int pr[][] = new int[l][l];
        //存放串行计算结果
        int sr[][] = new int[l][l];
        //子线程数量
        System.out.println("请输入线程数量");
        int t = scan.nextInt();
        int threadNum = t;
        //子线程的分片计算间隔
        int gap = A.length / threadNum;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        // 为A和B矩阵随机赋值

        for (int i = 0; i < l; i++)
            for (int j = 0; j <l; j++) {
                A[i][j] = (int) (Math.random() * 50);
                B[i][j] = (int) (Math.random() * 50);
            }
        // 并行计算
        startTime = System.currentTimeMillis();
        for (int i = 0; i < threadNum; i++) {
            new Assignment(A, B, i, gap, pr, countDownLatch).start();
        }
        //
        System.out.println("主线程:"+Thread.currentThread().getName()+" 开始等待>>>>>>>>>>>>>>");
        countDownLatch.await();
        System.out.println("主线程:"+Thread.currentThread().getName()+" 结束等待<<<<<<<<<<<<<<");
        finalTime = System.currentTimeMillis();
        System.out.println("并行计算开始时刻:" + (startTime));
        System.out.println("并行计算结束时刻:" + (finalTime));
        System.out.println("并行计算运行时间:" + (finalTime - startTime));

        // 串行计算
        startTime = System.currentTimeMillis();
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                for (int k = 0; k <l; k++)
                    sr[i][j] += A[i][k] * B[k][j];
            }
        }
        finalTime = System.currentTimeMillis();
        System.out.println("串行计算开始时刻:" + (startTime));
        System.out.println("串行计算结束时刻:" + (finalTime));
        System.out.println("串行计算运行时间:" + (finalTime - startTime));
    }
}
