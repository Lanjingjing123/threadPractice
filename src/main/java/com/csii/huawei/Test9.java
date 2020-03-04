package com.csii.huawei;

import java.util.*;

/**
 * @author lanjingjing
 * @description 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 * @date 2020/3/1
 *
 * eg:
 * input: 9876673
 * output: 37689
 *
 * 思路：
 * 1. 将input 转换为字符数组 chars
 * 2. 遍历 chars（从最后开始）,存入set 链表里面
 *
 */
public class Test9 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        while (in.hasNext()){
            String s = in.nextLine();

            char[] chars = s.toCharArray();
            // 存储接口
            Map<Integer,Character> map = new HashMap<>();


            // map k: index v:value(不重复)
            for (int i=chars.length-1;i>=0;i--){
                if (map.containsValue(chars[i])){ // 存在，开始下一次循环
                    continue;
                } else{ // 不存在，直接存
                    map.put(i,chars[i]);

                }

            }

            // map按照key 降序排序
            Map<Integer,Character> resultMap = soryByKey(map);
            Iterator<Map.Entry<Integer, Character>> iterator = resultMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<Integer, Character> next = iterator.next();
                System.out.print(next.getValue());
            }

        }




    }

    public static  Map<Integer,Character> soryByKey( Map<Integer,Character> map){
        Map<Integer,Character> resultMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -(o1.compareTo(o2));
            }
        });

        resultMap.putAll(map);
        return resultMap;
    }
}
