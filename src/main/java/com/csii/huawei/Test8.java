package com.csii.huawei;

import java.util.*;

/**
 * @author lanjingjing
 * @description 数据表记录包含表索引和数值（int范围的整数），请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出
 * @date 2020/3/1
 * 输入数据：
 * 4
 * 0 1
 * 0 2
 * 1 2
 * 3 4
 * 输出数据：
 * 0 3
 * 1 2
 * 3 4
 * 思路：1. 使用 List<Map<index,value>> 数据结构存储录入数据
 * 2. 根据key进行累加value
 */
public class Test8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
//        Map<String ,Long> map =null;
//        long[] nums = new long[10];
//        Random random = new Random();
//        List<Map<String ,Long>> longList = new ArrayList<Map<String ,Long>>();
//
//        for (int i=0 ;i<10;i++){
//            map = new HashMap<>();
//            int num = random.nextInt(100);
//            map.put(""+i,(long)num);
//            longList.add(map);
//        }
//
//        longList.forEach((it)->{
//            System.out.println(it);
//        });
//        longList.sort(new MyComparator());
//
//        longList.forEach((it)->{
//            System.out.println(it);
//        });
        while (input.hasNext()) {
            // 链表长度
            Map<Long, Long> map = null;
            // 输入链表
            List<Map<Long, Long>> list = new ArrayList<>();
            // 结果Map  k:0 v:3
            Map<Long, Long> resultMap = new HashMap<>();

            Long size = Long.parseLong(input.nextLine());
            // 录入指定长度的数据
//            System.out.println("长度：" + size + "-----");

            // 对input 进行赋值
            for (int i = 0; i < size; i++) {
                map = new HashMap<>();
                String line = input.nextLine();
                String[] arr = line.split(" ");
                map.put(Long.parseLong(arr[0]), Long.parseLong(arr[1]));
                list.add(map);

            }
            list.forEach((it) -> {
//                System.out.println(it);
                // k:0 v:1
                // k:0 v:2
                // k:1 v:2
                Iterator<Map.Entry<Long, Long>> iterator = it.entrySet().iterator();

                while (iterator.hasNext()) {
                    Map.Entry<Long, Long> entry = iterator.next();
                    Long key = entry.getKey();
                    Long value = entry.getValue();

                    // 根据key查找resuletMap里是否存在
                    if (resultMap.containsKey(key)) {//存在,累加
                        Long origValue = resultMap.get(key);
                        resultMap.put(key, value + origValue);

                    } else {//不存在，直接存入
                        resultMap.put(key, value);
                    }
                }
            });


            // 对resultMap进行排序
            Map<Long,Long> finalMap = sortMapBykey(resultMap);
            Iterator<Map.Entry<Long, Long>> iterator = finalMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<Long, Long> en = iterator.next();
                System.out.println(en.getKey()+" "+en.getValue());
            }

        }
    }

//    static class MyComparator implements Comparator<Map<String, Long>> {
//
//
//        @Override
//        public int compare(Map<String, Long> o1, Map<String, Long> o2) {
//
//            String key1 = "";
//            Long value1 = 0L;
//            String key2 = "";
//            Long value2 = 0L;
//            if (o1 != null && o2 != null && o1.size() == 1 && o2.size() == 1) {
//                Iterator<Map.Entry<String, Long>> iterator1 = o1.entrySet().iterator();
//                while (iterator1.hasNext()) {
//                    Map.Entry<String, Long> entry = iterator1.next();
//
//                    key1 = entry.getKey();
//                    value1 = entry.getValue();
//
//                }
//                Iterator<Map.Entry<String, Long>> iterator2 = o2.entrySet().iterator();
//                while (iterator2.hasNext()) {
//                    Map.Entry<String, Long> entry = iterator2.next();
//                    key2 = entry.getKey();
//                    value2 = entry.getValue();
//                }
//            }
//
//            return value1.compareTo(value2);
//        }
//    }

    /**
     * 按照值排序
     * 思路：1.将入参map<K,V> 使用list包装 List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>(map.entrySet()) ;
     *      2. 对List进行排序 Collections.sort(list, new Comparator<Map.Entry<K, V>>() @Override
     *             public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
     *                 int result = (o1.getValue()).compareTo(o2.getValue());
     *                 return result;
     *             }
     *      3. 遍历排序后的List 进行，重新赋值输出
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
//  public static <K, V extends Comparable> Map<K, V> sortByValueDescending(Map<K, V> map)
    public static <K extends Comparable<? super V > , V extends Comparable<? super V >> Map<K,V> sortMapByVaLue(Map<K,V> map){
        List<Map.Entry<K,V>> list = new LinkedList<Map.Entry<K,V>>(map.entrySet()) ;
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int result = (o1.getValue()).compareTo(o2.getValue());
                return result;
            }
        });

        Map<K,V> resultMap = new LinkedHashMap<K,V>();

        for (Map.Entry<K,V> entry:list) {
            resultMap.put(entry.getKey(),entry.getValue());
        }
        return  resultMap;
    }


    /**
     * 按照key排序 ,使用TreeMap(保证健的有序性)
     *
     * @param map
     * @return
     */
    public static Map<Long,Long> sortMapBykey(Map<Long,Long> map){
        Map<Long,Long> resultMap = new TreeMap<>(new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                return o1.compareTo(o2);
            }
        });

        resultMap.putAll(map);

        return resultMap;

    }

}
