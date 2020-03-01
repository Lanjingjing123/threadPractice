package com.csii.huawei;

import java.util.Scanner;

/**
 * 字符串分离：
 * <p>
 * •连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * •长度不是8整数倍的字符串请在后面补数字0，空字符串不处理
 */
public class test4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        char[] arr = new char[8];
        StringBuffer sb = new StringBuffer();

        while (input.hasNext()) {
            String line = input.next();
            char[] chars = line.toCharArray();
            if (chars.length > 8) { // 字符串>8
                //  整行数
                int count = chars.length/8;
                // 余数
                int yu = chars.length%8;
                for (int i=0;i<count;i++){
                    System.out.println(line.substring(8*i,8*i+8));
                }
                if (yu >0 ){

                    sb.append(line.substring(8*count,chars.length));
                    for (int j=0;j<8-yu;j++){
                        sb.append("0");
                    }

                    System.out.println(sb.toString());
                    sb.delete(0,sb.length());
                }
            } else { // 小于8,补0
                sb.append(line);
                for (int j=chars.length;j<8;j++) {
                    sb.append("0");
                }
                System.out.println(sb.toString());
                sb.delete(0,sb.length());
            }
        }
    }
}
