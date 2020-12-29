package pers.dyx.java;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author: dyx
 * @date: 2018/8/23 09:06
 * @description: 循环HashMap的4种方法
 */
public class LoopHashMap {
    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        hashMap.put(1, 1);
        hashMap.put(2, 2);
        //* 3种hashMap循环方式
        // for each map.entrySet()
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        //显示调用map.entrySet()的集合迭代器
        Iterator<Map.Entry<Integer, Integer>> iterator2 = hashMap.entrySet().iterator();
        while (iterator2.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator2.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        // for each map.keySet()，再调用get获取
        for (Integer key : hashMap.keySet()) {
            System.out.println(key);
            System.out.println(hashMap.get(key));
        }
    }
}
