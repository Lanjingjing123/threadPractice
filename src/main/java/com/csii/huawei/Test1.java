package com.csii.huawei;

import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        while(sc.hasNext()){
//            String str =sc.nextLine();
//            String[] arr = str.split(" ");
//            System.out.println(arr[arr.length-1].length());
//        }


        Scanner input = new Scanner(System.in);

        String firstLine = input.nextLine();
        String secondLine = input.nextLine();
        char character = secondLine.charAt(0);
        char[] arr = firstLine.toCharArray();


        int count = 0;
        for (char item : arr) {

            if (item >= 'A' && item <= 'z') {
                char c1 = (char) ((int) item + 32);
                char c2 = (char) ((int) item - 32);
                if (character == item ||
                        character == c1 ||
                        character == c2) {

                    count++;
                }
            }

        }

        System.out.println(count);
    }
}
