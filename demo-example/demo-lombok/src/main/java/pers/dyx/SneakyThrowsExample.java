package pers.dyx;

import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

/**
 * 还在手动捕获并抛出异常？使用@SneakyThrows注解自动实现试试！
 *
 * @author dyx
 * @date 2020/12/29 16:05
 */
public class SneakyThrowsExample {

    //自动抛出异常，无需处理
    @SneakyThrows(Exception.class)
    public static byte[] str2byte(String str) {
        return str.getBytes(StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        String str = "Hello World!";
        System.out.println(str2byte(str).length);
    }
}
