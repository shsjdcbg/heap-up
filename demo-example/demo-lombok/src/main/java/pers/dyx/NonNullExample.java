package pers.dyx;

import lombok.NonNull;

/**
 * 在方法上使用@NonNull注解可以做非空判断，如果传入空值的话会直接抛出NullPointerException
 *
 * @author dyx
 * @date 2020/12/29 15:54
 */
public class NonNullExample {
    private String name;

    public NonNullExample(@NonNull String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        new NonNullExample("test");
        //会抛出NullPointerException
        new NonNullExample(null);
    }
}
