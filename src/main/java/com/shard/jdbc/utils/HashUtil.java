package com.shard.jdbc.utils;

import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class HashUtil {
    /**
     * bkd一致性哈希算法
     *
     * @param str
     * @return
     */
    public static int bkdrhash(String str) {
        final int seed = 131;

        int hash = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = hash * seed + (int) str.charAt(i);
        }

        return hash & 0x7FFFFFFF;
    }

    /**
     * pjw一致性哈希算法
     *
     * @param str
     * @return
     */
    public static int pjwHash(String str) {
        int BitsInUnignedInt = 32;
        int ThreeQuarters = 24;
        int OneEighth = 4;
        int HighBits = (int) (0xFFFFFFFF) << (BitsInUnignedInt - OneEighth);
        int hash = 0;
        int test = 0;

        for (int i = 0; i < str.length(); i++) {
            hash = (hash << OneEighth) + (int) str.charAt(i);
            if ((test = hash & HighBits) != 0) {
                hash = ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
            }
        }

        return hash & 0x7FFFFFFF;
    }

    /**
     * rs一致性哈希算法
     *
     * @param value
     * @return
     */
    public static int rsHash(String value) {
        int one = 378551;
        int two = 63689;
        int hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = hash * two + value.charAt(i);
            two = two * one;
        }
        return (hash & 0x7FFFFFFF);
    }

    /**
     * 在分布式服务中，获取路由
     *
     * @param num
     * @param str
     * @return
     */
    public static int getIndex1(Integer num, String str) {
        return pjwHash(str) % num;
    }

    public static int getIndex(Integer num, String str) {
        return rsHash(str) % num;
    }

    /**
     * 计算能均匀分布的数据库数量和表数量
     * 默认为10000个数,需要手动选择符合条件的数据库数量和表数量: 数据库数量*表数量=250
     * @param x
     * @param y
     * @return
     */
    public static Map<String, String> cal(int x, int y) {
        Map<String, String> result = new TreeMap<>();
        for (int c = 1; c < x; c++) {
            for (int d = 1; d < y; d++) {
                Map<String, Integer> map =getCountMap(x,y);
                List<Integer> result4 = map.values().stream().filter(e -> e.equals(0))
                        .collect(Collectors.toList());
                if (CollectionUtils.isEmpty(result4)) {
                    result.put(c + "" + d, c + "$" + d);
                }
            }
        }
        return result;
    }

    /**
     * 统计分布的总数量
     * @param a
     * @param b
     * @return
     */
   public static  Map<String,Integer> getCountMap(int a,int b){
       Map<String, Integer> map = new HashMap<>();
       for (int i = 0; i < a; i++) {
           for (int j = 0; j < b; j++) {
               String s = i + "" + j;
               map.put(s, 0);
           }
       }
       for (int i = 0; i < 10000; i++) {
           int data = getIndex(a, String.valueOf(i));
           int col = getIndex(b, String.valueOf(i));
           String s = data + "" + col;
           int count = map.get(s) + 1;
           map.put(s, count);
       }
//       map.forEach((x, y) -> {
//           System.out.println("key=" + x + " value=" + y);
//       });
       return map;
   }
}
