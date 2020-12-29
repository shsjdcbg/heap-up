package pers.dyx.java;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * 在Java中，当浮点数(float、double)的整数部分达到8位及以上，会以科学计数法表示
 *
 * @author dyx
 * @date 2020/10/26 12:37
 */
public class NumberFormatBum {

    public static void main(String[] arg) {
        //在Java中，当浮点数(float、double)的整数部分达到8位及以上，会以科学计数法表示
        double firstAmount = 2700000D;
        double secondAmount = 27000000D;
        double thirdAmount = 2700000.25D;
        double fourthAmount = 27000000.25D;

        //2700000.0
        System.out.println(firstAmount);
        //2.7E7
        System.out.println(secondAmount);
        //2700000.25
        System.out.println(thirdAmount);
        //2.700000025E7
        System.out.println(fourthAmount);


        // 如果不想用科学计数法显示，而是显示金额本身，有以下2种解决方案：

        // 方案二：使用NumberFormat
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);

        System.out.println(numberFormat.format(secondAmount));
        System.out.println(numberFormat.format(fourthAmount));

        // 方案二：使用BigDecimal(推荐)
        System.out.println(new BigDecimal(String.valueOf(secondAmount)).setScale(2, RoundingMode.HALF_UP).toString());
        System.out.println(new BigDecimal(String.valueOf(fourthAmount)).setScale(2, RoundingMode.HALF_UP).toString());
    }
}
