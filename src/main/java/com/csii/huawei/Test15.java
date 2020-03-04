package com.csii.huawei;

import java.util.Scanner;

/**
 * @author lanjingjing
 * @description 输入一个int型的正整数，计算出该int型数据在内存中存储时1的个数。
 * @date 2020/3/2
 */
public class Test15 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (input.hasNext()){
            String s = input.nextLine();
            Long bin = Long.parseLong(s,10);
            int count = 0;
            String s1 = Long.toBinaryString(bin);
            char[] chars = s1.toCharArray();
            for (char c : chars) {
                if ( c == '1'){
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
