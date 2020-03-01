package com.csii.huawei;

import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){

            int num = sc.nextInt();
            TreeSet<Integer> set = new TreeSet<Integer>();
            for(int i = 0 ; i < num ;i++){
                int curr = sc.nextInt();
                set.add(curr);
            }
            for(Integer i : set){
                System.out.println(i);
            }
        }

    }

    static class MyConparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }
}
