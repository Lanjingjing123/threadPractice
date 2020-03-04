package com.csii.huawei;

import java.util.Scanner;

/**
 * @author lanjingjing
 * @description 输入一个整数，将这个整数以字符串的形式逆序输出
 * @date 2020/3/1
 */
public class Test11 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()){
            String s = input.nextLine();
            char[] chars = s.toCharArray();
            for (int i= chars.length-1; i>=0 ;i--){
                System.out.print(chars[i]);
            }
        }
    }
}
