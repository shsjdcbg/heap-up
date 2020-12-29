package pers.dyx.java;

/**
 * @author: dyx
 * @date: 2018/8/23 09:51
 * @description:
 */
public class StringFormat {
    public static void main(String[] args) {
        //对于字符串，即是只保留4个字符
        System.out.println(String.format("%.4s", "23.456789"));
        //保留小数点后4位
        System.out.println(String.format("%.4f", Double.parseDouble("23.456789")));
        System.out.println(String.format("%.4f", 23.456789));
    }
}
