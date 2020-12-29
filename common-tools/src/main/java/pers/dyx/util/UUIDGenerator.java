package pers.dyx.util;

import java.util.UUID;

/**
 * @author: dyx
 * @date: 2018/8/23 10:06
 * @description: UUID生成
 */
public class UUIDGenerator {

    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.replace("-", "");
        return str + "," + temp;
    }

    public static String[] getUuid(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUuid();
        }
        return ss;
    }

    public static void main(String[] args) {
        String[] ss = getUuid(10);
        for (int i = 0; i < ss.length; i++) {
            System.out.println("ss[" + i + "]=====" + ss[i]);
        }
    }
}
