package pers.dyx.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import pers.dyx.gson.vo.BagOfPrimitives;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author dyx
 * @date 2020/12/31 17:36
 */
public class GsonDemo {
    public static void main(String[] args) {
        // 序列化
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("abcd"));
        System.out.println(gson.toJson(10L));
        int[] values = {1};
        System.out.println(gson.toJson(values));

        // 反序列化
        int one = gson.fromJson("1", int.class);
        Integer one1 = gson.fromJson("1", Integer.class);
        Long one2 = gson.fromJson("1", Long.class);
        Boolean aBoolean = gson.fromJson("false", Boolean.class);
        String str = gson.fromJson("\"abc\"", String.class);
        String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);


        // 关于对象的例子
        // 序列化
        BagOfPrimitives obj = new BagOfPrimitives();
        String json = gson.toJson(obj);
        System.out.println(json);
        // 反序列化
        BagOfPrimitives obj2 = gson.fromJson(json, BagOfPrimitives.class);


        // 关于数组的例子
        Integer[] ints = {1, 2, 3, 4, 5};
        String[] strings = {"abc", "def", "ghi"};
        System.out.println(gson.toJson(ints));     // ==> [1,2,3,4,5]
        System.out.println(gson.toJson(strings));  // ==> ["abc", "def", "ghi"]
        int[] ints2 = gson.fromJson("[1,2,3,4,5]", int[].class);


        // 集合、泛型例子
        List<Integer> collection = Arrays.asList(ints);
        json = gson.toJson(collection);  // ==> json is [1,2,3,4,5]
        System.out.println(json);
        Type collectionType = new TypeToken<Collection<Integer>>() {
        }.getType();
        Collection<Integer> collection1 = gson.fromJson(json, collectionType);

    }
}
