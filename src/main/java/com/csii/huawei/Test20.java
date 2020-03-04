package com.csii.huawei;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lanjingjing
 * @description 密码
 * @date 2020/3/2
 */
public class Test20 {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            Map<String,String> map = new HashMap<>();
            String s = scanner.nextLine();
            char[] chars = s.toCharArray();
            // 是否重复标志 true -重复 false-不重复
            Boolean flag = false;
            if (s.length()<=8){
                System.out.println("NG");
                continue;
            }
            if (!passwordValidate(s)){
                System.out.println("NG");
                continue;
            }

            for (int i=0 ;i<chars.length;i++){
                // key:str value:1 ,若不存在此key则说明不重复，直接插入，循环结束无
                for (int j=i+3 ;j<chars.length;j++){
                    String substring = s.substring(i, j);
                    if (map.containsKey(substring)){ // 已经存在key：substring 说明重复
                        System.out.println(substring);
                        flag = true;
                        break;
                    }else {
                        map.put(substring,"1");
                    }
                }

            }

            if (flag){ // 有重复
                System.out.println("NG");

            }else {
                System.out.println("OK");
            }

        }
    }

    /**
     * 是否满足 至少3种条件
     * @param str
     * @return
     */
    public static boolean passwordValidate(String str){
        List<String> list= new ArrayList<>();
        list.add("[a-z]+");
        list.add("[A-Z]+");
        list.add("[0-9]+");
        list.add("[\\W]+");
        int count = 0;
        for (String regx:list) {
//            System.out.println(regx);
            Pattern pattern = Pattern.compile(regx);
            Matcher matcher = pattern.matcher(str);
            if (matcher.find()){
                count++;
            }
        }

        if (count>=3){
            return true;
        }

        return false;
    }
}
