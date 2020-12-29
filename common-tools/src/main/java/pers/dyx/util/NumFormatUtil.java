package pers.dyx.util;

import java.math.BigDecimal;

/**
 * 数字格式化工具类
 *
 * @author dyx
 * @date 2017-9-12 08:47:17
 */
public class NumFormatUtil {


    public static Double formatDouble(Double d, int num) {
        BigDecimal b = new BigDecimal(d);
        d = b.setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
        return d;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(NumFormatUtil.formatDouble(123.12923D, 2));
    }
}
