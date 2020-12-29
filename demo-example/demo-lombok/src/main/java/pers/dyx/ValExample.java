package pers.dyx;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 使用val注解可以取代任意类型作为局部变量
 * 当我们使用了val注解后，Lombok会从局部变量的初始化表达式推断出具体类型
 *
 * @author dyx
 * @date 2020/12/29 15:51
 */
public class ValExample {
    public static void example() {
        //val代替ArrayList<String>和String类型
        val example = new ArrayList<String>();
        example.add("Hello World!");
        val foo = example.get(0);
        System.out.println(foo.toLowerCase());
    }

    public static void example2() {
        //val代替Map.Entry<Integer,String>类型
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }

    public static void main(String[] args) {
        example();
        example2();
    }
}
