package pers.dyx.java;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * BigDecimal Demo
 * 1、在使用BigDecimal类型进行计算时，比加、减、乘、除、比较大小时，一定要保证参与计算的两个值不能为空
 * 2、java.lang.ArithmeticException: Non-terminating decimal expansion; no exact representable decimal result.
 * 报错原因是因为无法整除，导致结果是无限循环小数：解决方案是指定下舍入模式，比如我们最常用的四舍五入模式：
 * 3、尽量不要在项目中使用new BigDecimal("0")，而是使用BigDecimal提供的常量BigDecimal.ZERO。
 * BigDecimal zero = BigDecimal.ZERO;
 * BigDecimal one = BigDecimal.ONE;
 * BigDecimal ten = BigDecimal.TEN;
 *
 * @author dyx
 * @date 2020/10/26 12:55
 */
public class BigDecimalDemo {

    public static void main(String[] arg) {

        //加法
        BigDecimal number1 = new BigDecimal("88.88");
        BigDecimal number2 = new BigDecimal("11.11");

        BigDecimal number3 = number1.add(number2);
        System.out.println("number1 add number2 = " + number3);

        //减法
        BigDecimal number4 = number1.subtract(number2);
        System.out.println("number1 subtract number2 = " + number4);

        //乘法
        BigDecimal number5 = number1.multiply(number2);
        System.out.println("number1 multiply number2 = " + number5);

        //除法
        BigDecimal number6 = number1.divide(number2);
        System.out.println("number1 divide number2 = " + number6);

        // 因为上面2个数可以整除，所以这么用没有问题，不过一但不能被整除，这么用就会有潜在的风险，会抛出java.lang.ArithmeticException异常，所以强烈建议像下面这样使用：
        BigDecimal number7 = number1.divide(number2, 2, RoundingMode.HALF_UP);
        System.out.println("number1 divide number2 = " + number7);

        // 保留小数位数
        // 如果我们想对BigDecimal类型保留小数位数，可以使用setScale() 方法，使用方法如下所示：
        // 保留3位小数,四舍五入
        BigDecimal number8 = number3.setScale(3, RoundingMode.HALF_UP);
        System.out.println("number3 setScale = " + number8);

        // 比较大小
        // BigDecimal比较大小，可以使用compareTo()方法，使用方法如下所示：
        System.out.println("number1 compareTo number2 = " + number1.compareTo(number2));
        System.out.println("number1 compareTo number3 = " + number1.compareTo(number3));
        System.out.println("number1 compareTo number4 = " + number1.compareTo(number4));
    }
}
