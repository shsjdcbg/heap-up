package pers.dyx.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: dyx
 * @date: 2018/8/23 09:38
 * @description: 日期操作公共类
 */
public class CalendarUtils {

    private final static String YYYY_MM_DD = "yyyyMMdd";

    /**
     * 计算某个日期n天前后的日期，并指定打印格式。正值表示向后计算，负数表示向前计算。
     *
     * @param startDay 开始日期
     * @param dayCount 向前或向后几天
     * @return 向前或向后几天的日期
     * @throws ParseException 日期格式不正确
     */
    public static String getBeforeOrAfterDay(String startDay, int dayCount) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        Date date = sf.parse(startDay);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayCount);
        Date day = calendar.getTime();
        return sf.format(day);
    }

    /**
     * 计算某个日期过去N天所有日期的集合
     *
     * @param day      开始日期
     * @param dayCount 向前或向后几天
     * @return 向前或向后几天的日期
     * @throws ParseException 日期格式不正确
     */
    public static String[] getLastDays(String day, int dayCount) throws ParseException {
        String[] days = new String[dayCount];
        for (int i = 0; i < dayCount; i++) {
            days[i] = getBeforeOrAfterDay(day, -i);
        }
        return days;
    }

    /**
     * 判断某个日期是星期几
     *
     * @param day 日期
     * @return 星期几  1：星期天，2：星期一.....7：星期六
     * @throws ParseException 日期格式不正确
     */
    public static int getWeekDay(String day) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        Date date = sf.parse(day);
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 判断某个日期是否工作日，星期六及星期天为非工作日，其余为工作日。返回值1：工作日，0：休息日。
     *
     * @param day 日期
     * @return 是否为工作日
     * @throws ParseException 日期格式不正确
     */
    public static boolean getWorkDay(String day) throws ParseException {
        int weekDay = getWeekDay(day);
        return weekDay != Calendar.SATURDAY && weekDay != Calendar.SUNDAY;
    }

    /**
     * 判断某个时间是否工作时间。10:00~17:00为工作时间，21:00~06:00为休息时间。输入值为小时。返回值1：工作时间，0：休息时间，-1：其它时间。
     *
     * @param time 时间
     * @return 是否工作时间
     */
    public static String getWorkTime(String time) {
        int hour = Integer.parseInt(time);
        if (hour >= 10 && hour <= 16) {
            return "1";
        } else if ((hour >= 0 && hour <= 5) || (hour >= 21 && hour <= 23)) {
            return "0";
        } else {
            return "-1";
        }
    }

    /**
     * 获取过去24个小时整点
     *
     * @return 时间戳数组
     */
    public static long[] getIntegralTime() {
        long[] timeStramps = new long[24];
        long timeStramp = System.currentTimeMillis();
        String time = DateUtil.timeStampToDate(String.valueOf(timeStramp).substring(0, 10), null);
        long mill = timeStramp - Long.parseLong(time.substring(17, 19)) * 1000
                - Long.parseLong(time.substring(14, 16)) * 60 * 1000;
        timeStramps[23] = mill;
        for (int i = 0; i < 23; i++) {
            timeStramps[22 - i] = timeStramps[23 - i] - 3600000;
        }
        return timeStramps;
    }

    public static void main(String[] args) throws ParseException {
        System.out.println(getBeforeOrAfterDay("20170201", -1));
        System.out.println(getWeekDay("20170216"));
        System.out.println(Arrays.toString(getLastDays("20170216", 30)));
        System.out.println(Arrays.toString(getIntegralTime()));
    }

}
