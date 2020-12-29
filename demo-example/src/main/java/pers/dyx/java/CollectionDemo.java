package pers.dyx.java;

import java.util.*;

/**
 * Java 集合
 *
 * @author dyx
 * @version 1.0
 * @date 2020/7/27 18:02
 */
public class CollectionDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("3");
        list.add("1");
        list.add(null);
        list.add("3");
        list.add(null);
        list.add("2");
        // [3, 1, null, 3, null, 2]，ArrayList元素存入集合的顺序和取出的顺序一致，元素可以重复，可以插入多个null元素
        System.out.println("ArrayList:" + list);
        list = new LinkedList<>();
        list.add("3");
        list.add("1");
        list.add(null);
        list.add("3");
        list.add(null);
        list.add("2");
        // [3, 1, null, 3, null, 2]，LinkedList元素存入集合的顺序和取出的顺序一致，元素可以重复，可以插入多个null元素
        // LinkedList与ArrayList的不同之处在于底层实现不同，ArrayList为数组，LinkedList为双向链表
        // ArrayList访问效率更高，LinkedList增加和删除效率更高
        System.out.println("LinkedList:" + list);

        Set<String> set = new HashSet<>();
        set.add("3");
        set.add("1");
        set.add(null);
        set.add("3");
        set.add(null);
        set.add("2");
        // [null, 1, 2, 3]，HashSet存入和取出顺序有可能不一致，不可以存储重复元素，只允许存入一个null元素，必须保证元素唯一性
        System.out.println("HashSet:" + set);

        set = new LinkedHashSet<>();
        set.add("3");
        set.add("1");
        set.add(null);
        set.add("3");
        set.add(null);
        set.add("2");
        //[3, 1, null, 2]，以元素的添加顺序访问集合的元素，不可以存储重复元素，只允许存入一个null元素，必须保证元素唯一性
        System.out.println("LinkedHashSet:" + set);

        Map<String, String> map = new HashMap<>();
        map.put("3", "c");
        map.put("1", "a");
        map.put(null, "1");
        map.put("3", "c");
        map.put(null, "1");
        map.put("2", "b");
        // {null=1, 1=a, 2=b, 3=c}，存储键、值和之间的映射。 Key无序，存入和取出顺序有可能不一致，唯一；value不要求有序，允许重复
        System.out.println("HashMap:" + map);
        
        map = new LinkedHashMap<>();
        map.put("3", "c");
        map.put("1", "a");
        map.put(null, "1");
        map.put("3", "c");
        map.put(null, "1");
        map.put("2", "b");
        // {3=c, 1=a, null=1, 2=b}，存储键、值和之间的映射。 Key有序，以元素的添加顺序访问集合的元素，唯一；value不要求有序，允许重复
        System.out.println("LinkedHashMap:" + map);
    }

}
