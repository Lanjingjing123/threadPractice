package com.csii.huawei;

import java.util.Scanner;

/**
 * @author lanjingjing
 * @description 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I” 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 * @date 2020/3/1
 */
public class Test13 {
    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);

        while (input.hasNext()){
            String s = input.nextLine();
            String reverse = reverse(s);
            System.out.println(reverse);
        }
    }

    /**
     * 反转句子
     *
     * @param sentence 原句子
     * @return 反转后的句子
     */
    public static String reverse(String sentence){
        StringBuffer sb = new StringBuffer();
        String[] words = sentence.split(" ");
        for (int i=words.length-1; i>=0 ;i--){
            sb.append(words[i]).append(" ");
        }

        return sb.toString();
    }
}
