package pers.dyx.java;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期时间
 *
 * @author dyx
 * @version 1.0
 * @date 2020/8/31 17:26
 */
public class DateDemo {

    public static void main(String[] args) {
        dateDemo();
        simpleDateFormatDemo();
        calendarDemo();
        localDemo();
    }

    private static void dateDemo() {
        Date a = new Date();
        System.out.println(a);

        Date b = new Date(System.currentTimeMillis());

        System.out.println(b);
        //常用方法：
        //年	必须加上1900
        System.out.println(a.getYear() + 1900);
        //月	0~11 必须加1
        System.out.println(a.getMonth() + 1);
        //日	1-31 不用加
        System.out.println(a.getDate());
        //转换成String
        System.out.println(a.toString());
        //转换为GNT时区
        System.out.println(a.toGMTString());
        //转换为本地时区
        System.out.println(a.toLocaleString());
    }

    private static void simpleDateFormatDemo() {
        Date aa = new Date();
        SimpleDateFormat aa1 = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        String str = aa1.format(aa);
        System.out.println(str);
        DateTimeFormatter w = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm:ss");
        String t = w.format(LocalDateTime.now());
        System.out.println(t);
    }

    private static void calendarDemo() {
        Calendar a = Calendar.getInstance();
        Date b = a.getTime();
        System.out.println(b);
        //年
        int year = a.get(Calendar.YEAR);
        //月
        int mon = a.get(Calendar.MONTH) + 1;
        //日
        int day = a.get(Calendar.DATE);
        //日
        int d = a.get(Calendar.DAY_OF_MONTH);

        System.out.println(year + "-" + mon + "-" + day);
        System.out.println(year + "年" + mon + "月" + d + "日");

        //从今年初到现在过去了多少天：
        int t = a.get(Calendar.DAY_OF_YEAR);
        System.out.println(t);

        //当前小时数
        int shi = a.get(Calendar.HOUR);
        System.out.println(shi + "时");
        //当前分钟数
        int fen = a.get(Calendar.MINUTE);
        System.out.println(fen + "分");
        //当前秒数
        int miao = a.get(Calendar.SECOND);
        System.out.println(miao + "秒");

        System.out.println(year + "年" + mon + "月" + day + "日" + shi + "时" + fen + "分" + miao + "秒");

        //格式化输出
        Date aa = new Date();
        SimpleDateFormat aa1 = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");


        System.out.println(aa1.format(aa));
        //从当前时间计算n天后的日期
        f2();
        //比较日期的先后，返回布尔值
        f3();
    }

    private static void f2() {
        Calendar c3 = Calendar.getInstance();
        c3.add(Calendar.DATE, +10);
        int year = c3.get(Calendar.YEAR);
        int month = c3.get(Calendar.MONTH) + 1;
        int date = c3.get(Calendar.DATE);

        System.out.println(year + "年" + month + "月" + date + "日");
    }

    private static void f3() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2020, Calendar.MAY, 12);
        Calendar c2 = Calendar.getInstance();
        c2.set(2020, Calendar.JULY, 10);
        //前面的与括号内相比较，在前还是在后(返回的是布尔值，true或者false）
        boolean b = c2.after(c1);
        System.out.println(b);
    }

    public static void localDemo() {
        localDemo1();
        localDemo2();
        localDemo3();
        localDemo4();
        localDemo5();
    }

    private static void localDemo1() {
        //当前日期
        LocalDate a = LocalDate.now();
        //当前时间
        LocalTime time = LocalTime.now();
        //当前年月日时分秒
        LocalDateTime b = LocalDateTime.now();
        System.out.println(a);
        System.out.println(time);
        System.out.println(b);
    }

    private static void localDemo2() {
        //更规范操作
        //当前年月日时分秒
        LocalDateTime a = LocalDateTime.now();
        //当前日期
        LocalDate a1 = a.toLocalDate();
        //当前时间
        LocalTime a2 = a.toLocalTime();
        System.out.println(a);
        System.out.println(a1);
        System.out.println(a2);
    }

    private static void localDemo3() {
        LocalDateTime a = LocalDateTime.now();
        //输出年
        System.out.println(a.getYear());
        //月(英文)
        System.out.println(a.getMonth());
        //月（中文）
        System.out.println(a.getMonthValue());
        //日
        System.out.println(a.getDayOfMonth());
        //今年第一天到现在有多少天；
        System.out.println(a.getDayOfYear());
        //星期
        System.out.println(a.getDayOfWeek());
        //时
        System.out.println(a.getHour());
        //分钟
        System.out.println(a.getMinute());
        //秒
        System.out.println(a.getSecond());
    }

    private static void localDemo4() {
        //给定时间并输出
        LocalDate a = LocalDate.of(2020, 8, 26);
        LocalTime b = LocalTime.of(9, 10, 3);
        LocalDateTime c = LocalDateTime.of(a, b);
        LocalDateTime d = LocalDateTime.of(2020, 8, 26, 9, 10, 3);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
    }

    private static void localDemo5() {
        //按照指定的格式进行日期时间的输出
        DateTimeFormatter w = DateTimeFormatter.ofPattern("yyyy年MM月dd日HH时mm分ss秒");
        String t = w.format(LocalDateTime.now());
        System.out.println(t);
    }
}
