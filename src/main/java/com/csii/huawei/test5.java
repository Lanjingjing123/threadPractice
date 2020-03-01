package com.csii.huawei;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个十六进制的数，输出该数值的十进制表示。（多组同时输入 ）
 *
 * 注：需要练习各个进制的转换，16进制转2进制，10转16进制，10转2
 */
public class test5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            String hex_num = input.nextLine();
            hex_num = hex_num.replace("0x","");


            long l = Long.parseLong(hex_num, 16);
            System.out.println(l);
        }
    }

}
