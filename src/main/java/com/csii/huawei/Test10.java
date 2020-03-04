package com.csii.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author lanjingjing
 * @description 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)，换行表示结束符，不算在字符里。不在范围内的不作统计。
 * @date 2020/3/1
 */
public class Test10 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String s = input.nextLine();
            char[] chars = s.toCharArray();
            int count = 0;
            Map<Integer,Character> map = new HashMap<>();
            for (int i = 0; i < chars.length; i++) {
                int trans = (int) chars[i];
                if (trans >= 0 && trans<=127){
                    if (map.containsValue(chars[i])){// 已经存在，不统计
                        continue;
                    }
                    map.put(i,chars[i]);
                    count ++;
                }
            }

            System.out.println(count);
        }

    }
}
