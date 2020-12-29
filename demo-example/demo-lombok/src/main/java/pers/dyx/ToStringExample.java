package pers.dyx;

import lombok.ToString;

/**
 * 把所有类属性都编写到toString方法中方便打印日志，是一件多么枯燥无味的事情。
 * 使用@ToString注解可以自动生成toString方法，默认会包含所有类属性，使用@ToString.Exclude注解可以排除属性的生成
 *
 * @author dyx
 * @date 2020/12/29 15:57
 */
@ToString
public class ToStringExample {
    @ToString.Exclude
    private Long id;
    private String name;
    private Integer age;

    public ToStringExample(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        ToStringExample example = new ToStringExample(1L, "test", 20);
        //自动实现toString方法，输出ToStringExample(name=test, age=20)
        System.out.println(example);
    }
}
