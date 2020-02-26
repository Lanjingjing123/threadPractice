package com.csii.ljj.thread;

import org.junit.Test;

import java.util.Scanner;

public class UnitTest {
    @Test
    public void test1(){
        String str = "hello world";
        String[] s = str.split(" ");
        int length = s[s.length - 1].length();
        System.out.println(length);
    }

    @Test
    public void test2(){
        String str = "ABCaD";
        char character = 'a';
        char[] arr = str.toCharArray();
        int count = 0 ;
        for (char item:arr) {
            if (character == item){
                count++;
            }
        }

        System.out.println(count);
    }

    @Test
    public void test3(){
        char AA = 'A';
        char aa = 'a';
        char ZZ = 'Z';
        char zz = 'z';
        System.out.println((int)AA);
        System.out.println((int)aa);
        System.out.println((int)ZZ);
        System.out.println((int)zz);

        System.out.println();
        String str = "ABCaD";
        char character = 'a';
        char[] arr = str.toCharArray();
        int count = 0 ;
        for (char item:arr) {
            char value = (char)((int)item+27);
            System.out.println(value);
            if (character == item){
                count++;
            }
        }

        System.out.println(count);
    }
}
