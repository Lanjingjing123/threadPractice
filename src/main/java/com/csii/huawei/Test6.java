package com.csii.huawei;



import java.util.*;

/**
 * 按照从小到大的顺序输出它的所有质数的因子，以空格隔开。最后一个数后面也要有空格。
 */
public class Test6 {
    private static List<Long> list = new ArrayList<>();


    public static void main(String[] args) {


        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {

            Long inputNum = input.nextLong();
            // 用于存储 被除数

            if (isSushu(inputNum)){
                System.out.println(inputNum);
                continue;
            }
            System.out.println(isSushu(inputNum));
            Map<String,Long> map = new HashMap<>();
            map.put("number",inputNum);
            map.put("flag",-1L);

            for (long i=2; i<map.get("number"); i++){
                boolean flag = isSushu(i);
                if (!flag){ // i 不是素数 直接跳过
                    continue;
                }
                call(map,i);

                if (map.get("flag")>0){ // 商已经是素数，不在循环
                    break;
                }
            }
            list.forEach((a)->{
                System.out.print(a+" ");
            });

            list.removeAll(list);
        }
    }



    /**
     * 判断素数
     */
    public static boolean isSushu(long num) {
        double k = Math.sqrt(num);

        int i = 0;
        for (i = 2; i <= k; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        if (i > k) {
            return true;
        }

        return false;
    }

    /**
     * 求因子
     * 180/2 =90 能除进，
     *      2存入list; 90 不是素数
     *          嵌套调用call()===>90/2=45 能除进
     *              2存入list; 45 不是素数
     *                   调用call()=======> 45/2 除不尽
     *                      exit  ========= number =45
     *
     *
     *
     *
     * @param map 被除数
     * @param chushu 除数
     */
    public static void call(Map<String,Long> map,long chushu) {
        Long number = map.get("number");
        if (number % chushu == 0) { // 能除进
            list.add(chushu);
//            System.out.println(number+"/"+chushu);
            long shang = number / chushu;
            // 判断商是不是素数
            boolean flag = isSushu(shang);
            if (flag){
                // 商是素数 ====>1.exit 2.改变number的值，不在循环
                list.add(shang);
                map.put("number",shang);
                // 商已经是素数，不在循环标志
                map.put("flag",1L);

            } else{ // 商不是素数，继续嵌套调用
                // 改变number的值，减少外层循环调用的次数
                map.put("number",shang);
                call(map,chushu);

            }

        }else{ // 除不尽
//            System.out.println(number+"/"+chushu+" 除不尽，开始下一次循环");
        }
    }

}
