package com.csii.huawei;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lanjingjing
 * @description 给定n个字符串，请对n个字符串按照字典序排列
 * @date 2020/3/1
 */
public class Test14 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<String> list = new LinkedList<>();

        while (input.hasNext()){
            String num = input.nextLine();
            Long cout = Long.parseLong(num);

            for (long i=0L;i < cout; i++){
                String s = input.nextLine();
                list.add(s);
            }
            list.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            });
            list.forEach((it)->{
                System.out.println(it);
            });

        }


    }
}
