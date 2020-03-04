package com.csii.huawei;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @author lanjingjing
 * @description 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整
 * @date 2020/3/1
 *
 * 思路： 1. 输入 浮点型类型
 *       2. 转为bigdicimal
 *       3. 向上取整
 */
public class Test7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (input.hasNext()){
            BigDecimal in = input.nextBigDecimal();
//            System.out.println(in);
            BigDecimal out = in.setScale(0,BigDecimal.ROUND_HALF_UP);
            System.out.println(out);

        }
    }
}
