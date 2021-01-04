package pers.dyx.utils;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期实用工具类
 *
 * @author dyx
 */
public class DateUtil {

    private final static String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param format  日期格式
     * @return 日期字符串
     */
    public static String timeStampToDate(String seconds, String format) {
        if (StringUtils.isEmpty(seconds)) {
            return StringUtils.EMPTY;
        }
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.parseLong(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param dateStr 字符串日期
     * @param format  日期格式
     * @return 日期字符串
     * @throws ParseException 日期格式转换失败时
     */
    public static String dateToTimeStamp(String dateStr, String format) throws ParseException {
        if (StringUtils.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return String.valueOf(sdf.parse(dateStr).getTime() / 1000);
    }

    /**
     * @param dateString yyyy-MM-dd
     * @return Date
     * @throws ParseException 日期格式转换失败时
     */
    public static Date stringToDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateString);
    }

    /**
     * 返回当前日期
     *
     * @return String
     */
    public static String getDateLong() {
        return DateFormatUtils.format(new Date(), "yyyyMMdd");
    }

    /**
     * 返回当前日期
     *
     * @return String
     */
    public static String getDate() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd");
    }

    /**
     * 返回日期yyyy-MM
     *
     * @return String
     */
    public static String getDateYearMonth(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM");
    }

    /**
     * 返回日期yyyy
     *
     * @return String
     */
    public static String getDateYear(Date date) {
        return DateFormatUtils.format(date, "yyyy");
    }

    /**
     * 返回日期yMM
     *
     * @return String
     */
    public static String getDateMonth(Date date) {
        String ym = DateFormatUtils.format(date, "yyyy-MM");
        return ym.substring(5);
    }

    /**
     * 返回日期yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd");
    }

    /**
     * 返回日期yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String getTime(Date date) {
        return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回当前日期时间
     *
     * @return String
     */
    public static String getDateTime() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 返回当前日期时间
     *
     * @return String
     */
    public static String getDateRoundMinute() {
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm");
    }

    /**
     * 获取日期200-10-5
     *
     * @param str 原日期类型yyyy-MM-dd HH:mm:ss.S
     * @return
     */
    public static String getDateTime(String str) {
        if (str == null || "".equals(str)) {
            return "";
        }
        DateFormat df = DateFormat.getDateInstance();
        Date d = null;
        try {
            d = df.parse(str);
        } catch (Exception e) {
            return null;
        }
        return DateFormatUtils.format(d, "yyyy-MM-dd");
    }

    /**
     * <p>Description [获取当前年月日+00:00:00]</p>
     *
     * @return
     * @author : wangjl
     * @update :
     * @date : 2015-12-3
     */
    public static Date getCurrentDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strCurDate = sdf.format(date) + " 00:00:00";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curdate = null;
        try {
            curdate = fmt.parse(strCurDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return curdate;
    }

    public static Date strToDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(formatter.format(new Date()), pos);
        return strtodate;
    }

    /**
     * 返回当前时间
     *
     * @return String
     */
    public static String getTime() {
        return DateFormatUtils.format(new Date(), "HHmmssSSS");
    }

    /**
     * 返回当前时间
     *
     * @return String
     */
    public static String getCurrentTime() {
        return DateFormatUtils.format(new Date(), "HH:mm:ss");
    }

    /**
     * 获取月的实现
     *
     * @param date
     * @return String
     */
    public static String getEndTime(Date date, int month, boolean endmonth) {
        String ds = null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, month);
        if (endmonth) {
            int i = c.getActualMaximum(Calendar.DAY_OF_MONTH);
            c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), i, 23, 59, 59);
        }
        ds = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd HH:mm:ss");
        return ds;

    }

    /**
     * 获取天的时限
     *
     * @param date
     * @return String
     */
    public static String getEndTime(Date date, int days) {
        String ds = null;
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        c.add(Calendar.DATE, days);
        ds = DateFormatUtils.format(c.getTime(), "yyyy-MM-dd HH:mm:ss");
        return ds;
    }

    /**
     * 返回中文日期
     *
     * @return String
     */
    public static String getGBDateYearMonth(Date date) {
        String gbdate = null;
        try {
            gbdate = DateFormatUtils.format(date, "yyyy年MM月");
        } catch (Exception pe) {
        }
        return gbdate;
    }

    /**
     * 返回中文日期
     *
     * @return String
     */
    public static String getGBDate(String dateStr) {
        DateFormat df = DateFormat.getDateInstance();
        String gbdate = null;
        try {
            Date date = df.parse(dateStr);
            gbdate = DateFormatUtils.format(date, "yyyy年MM月dd日");
        } catch (ParseException pe) {
        }
        return gbdate;
    }

    /**
     * 返回日期
     *
     * @return String
     */
    public static String getStringDate(String dateStr, String pattern) {
        DateFormat df = DateFormat.getDateInstance();
        String gbdate = null;
        try {
            Date date = df.parse(dateStr);
            gbdate = DateFormatUtils.format(date, pattern);
        } catch (ParseException pe) {
        }
        return gbdate;
    }

    /**
     * 返回中文日期时间
     *
     * @return String
     */
    public static String getGBDateTime(String dateStr) {
        DateFormat df = DateFormat.getDateInstance();
        String gbdate = null;
        try {
            Date date = df.parse(dateStr);
            gbdate = DateFormatUtils.format(date, "yyyy年MM月dd日 HH时mm分ss秒");
        } catch (ParseException pe) {
        }
        return gbdate;
    }

    /**
     * 返回日期格式
     *
     * @return Date
     */
    public static Date getDate(String dateStr) {
        DateFormat df = DateFormat.getDateInstance();
        try {
            Date date = df.parse(dateStr);
            return date;
        } catch (Exception e) {
        }
        return null;
    }

    public static String getShortDate(String dateStr) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        DateFormat df = DateFormat.getDateInstance();
        try {
            Date date = df.parse(dateStr);
            return DateFormatUtils.format(date, "yyyy-MM-dd");
        } catch (Exception e) {
        }
        return null;
    }

    public static Date getDate(String dateStr, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            Date date = sdf.parse(dateStr);
            return date;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 返回日期格式
     *
     * @return String
     */
    public static String getDateStr(Date date) {
        String dateStr = null;
        try {
            dateStr = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
            return dateStr;
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 返回日期格式
     *
     * @return String
     */
    public static String getDateStr(Date date, String pattern) {
        String dateStr = null;
        try {
            dateStr = DateFormatUtils.format(date, pattern);
            return dateStr;
        } catch (Exception e) {

        }
        return "";
    }

    /**
     * 返回时间类型
     *
     * @param aValue
     * @return
     */
    public static java.sql.Timestamp parseTimestampFromFormats(String aValue) {
        if (aValue == null || "".equals(aValue)) {
            return null;
        }
        DateFormat df = DateFormat.getDateTimeInstance();
        if (aValue.indexOf(":") == -1) {
            aValue = " 00:00:00";
        } else if (aValue.indexOf(":") != -1
                && aValue.indexOf(":") == aValue.lastIndexOf(":")) {
            aValue = aValue + ":00";
        }
        try {
            Date date = df.parse(aValue);
            return new java.sql.Timestamp(date.getTime());
        } catch (Exception e) {

        }

        return null;
    }

    public static java.sql.Timestamp now() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
     * 获取服务器的系统时间
     *
     * @return Date 日期 格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentServerTime() {
        return getDateTime();
    }

    /**
     * 把时间格式转换成到分钟
     *
     * @param date 日期 格式：yyyy-MM-dd HH:mm:ss
     * @return String 日期 格式：yyyy-MM-dd HH:mm
     */
    public static String getDateTimeStringToMinute(String date) {
        if (date == null || "".equals(date)) {
            return "";
        }
        try {
            return DateFormatUtils.format(DateFormat
                    .getDateTimeInstance().parse(date), "yyyy-MM-dd HH:mm");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取时间到秒
     *
     * @param date 日期
     * @return
     */
    public static String getDateStrToSecond(Date date) {
        if (date == null) {
            return "";
        }
        try {
            return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取时间到分
     *
     * @param date 日期
     * @return
     */
    public static String getDateStrToMinute(Date date) {
        if (date == null) {
            return "";
        }
        try {
            return DateFormatUtils.format(date, "yyyy-MM-dd HH:mm");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 获取时间到日
     *
     * @param date 日期
     * @return
     */
    public static String getDateStrToDay(Date date) {
        if (date == null) {
            return "";
        }
        try {
            return DateFormatUtils.format(date, "yyyy-MM-dd");
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDateFromString(String dateStr) throws Exception {
        DateFormat df = DateFormat.getDateInstance();
        Date date = df.parse(dateStr);
        return date;

    }

    public static Date getDateTimeFromString(String dateStr) throws Exception {
        DateFormat df = DateFormat.getDateTimeInstance();
        Date date = df.parse(dateStr);
        return date;
    }


//	/**
//	 * 检查文本框中的日期时间格式
//	 *
//	 * @param source
//	 *            Text
//	 *
//	 * @return
//	 */
//	public static boolean checkDateTime(Text text) {
//
//		if (text.getText() != null && text.getText().trim().length() > 0) {
//			String str = text.getText().trim();
//			Date date = null;
//			try {
//				date = changeDate(str);
//				String sdate = DateUtil.getDateStrToMinute(date);
//				text.setText(sdate);
//			} catch (Exception e1) {
//				MessageDialog.showMessage("时间格式错误，请重新输入，正确格式为：2006-06-06 6:6");
//
//				text.setText("");
//
//				return false;
//			}
//		}
//		return true;
//	}
//	/**
//	 * 检查文本框中的日期时间格式
//	 *
//	 * @param source
//	 *            Text
//	 *
//	 * @return
//	 */
//	public static boolean checkDateTime(WText text) {
//
//		if (text.getText() != null && text.getText().trim().length() > 0) {
//			String str = text.getText().trim();
//			Date date = null;
//			try {
//				date = changeDate(str);
//				String sdate = DateUtil.getDateStrToMinute(date);
//				text.setText(sdate);
//			} catch (Exception e1) {
//				MessageDialog.showMessage("时间格式错误，请重新输入，正确格式为：2006-06-06 6:6");
//
//				text.setText("");
//
//				return false;
//			}
//		}
//		return true;
//	}
//	/**
//	 * 检查文本框中的日期时间格式
//	 *
//	 * @param source
//	 *            StyledText
//	 *
//	 * @return
//	 */
//	public static boolean checkDateTime(StyledText text) {
//
//		if (text.getText() != null && text.getText().trim().length() > 0) {
//			String str = text.getText().trim();
//			Date date = null;
//			try {
//				date = changeDate(str);
//				String sdate = DateUtil.getDateStrToMinute(date);
//				text.setText(sdate);
//			} catch (Exception e1) {
//				MessageDialog.showMessage("时间格式错误，请重新输入，正确格式为：2006-06-06 6:6");
//
//				text.setText("");
//				return false;
//			}
//		}
//		return true;
//
//	}
//
//	/**
//	 * 检查文本框中的日期格式
//	 *
//	 * @param source
//	 *            Text
//	 *
//	 * @return
//	 */
//	public static boolean checkDate(Text text) {
//
//		if (text.getText() != null && text.getText().trim().length() > 0) {
//			String str = text.getText().trim();
//			Date date = null;
//			try {
//				date = changeDate(str);
//				String sdate = DateUtil.getDateStrToDay(date);
//				text.setText(sdate);
//			} catch (Exception e1) {
//				MessageDialog.showMessage("时间格式错误，请重新输入，正确格式为：2006-06-06");
//
//				text.setText("");
//				return false;
//			}
//		}
//		return true;
//	}
//	/**
//	 * 检查文本框中的日期格式
//	 *
//	 * @param source
//	 *            Text
//	 *
//	 * @return
//	 */
//	public static boolean checkDate(WText text) {
//
//		if (text.getText() != null && text.getText().trim().length() > 0) {
//			String str = text.getText().trim();
//			Date date = null;
//			try {
//				date = changeDate(str);
//				String sdate = DateUtil.getDateStrToDay(date);
//				text.setText(sdate);
//			} catch (Exception e1) {
//				MessageDialog.showMessage("时间格式错误，请重新输入，正确格式为：2006-06-06");
//
//				text.setText("");
//				return false;
//			}
//		}
//		return true;
//	}
//	/**
//	 * 检查日期输入
//	 * @param text
//	 * @return
//	 */
//	public static boolean checkDateInput(Text text) {
//
//		if (text.getText() != null && text.getText().trim().length() > 0) {
//			String str = text.getText().trim();
//			Date date = null;
//			try {
//				date = changeDate(str);
//				String sdate = DateUtil.getDateStrToDay(date);
//				text.setText(sdate);
//			} catch (Exception e1) {
//				text.setText("");
//				text.setFocus();
//				MessageDialog.showMessage("时间格式错误，请重新输入，正确格式为：2006-06-06");
//				return false;
//			}
//		}
//		return true;
//	}
//
//	/**
//	 * 检查文本框中的日期格式
//	 *
//	 * @param source
//	 *            StyledText
//	 *
//	 * @return
//	 */
//	public static void checkDate(StyledText source) {
//
//		source.addFocusListener(new FocusAdapter() {
//
//			public void focusLost(FocusEvent e) {
//				StyledText text = (StyledText) e.getSource();
//				if (text.getText() != null
//						&& text.getText().trim().length() > 0) {
//					String str = text.getText().trim();
//					Date date = null;
//					try {
//						date = changeDate(str);
//						String sdate = DateUtil.getDateStrToDay(date);
//						text.setText(sdate);
//					} catch (Exception e1) {
//						MessageDialog
//								.showMessage("时间格式错误，请重新输入，正确格式为：2006-06-06");
//
//						text.setText("");
//					}
//				}
//			}
//		});
//	}

    private static Date changeDate(String str) throws Exception {
        Date date = null;
        if (str.indexOf(" ") >= 0) {
            String[] strArray = str.split(":");
            if (strArray != null) {
                if (strArray.length == 2) {
                    str = str + ":00";
                }
            }
            date = DateUtil.getDateTimeFromString(str);

        } else {

            date = DateUtil.getDateFromString(str);

        }

        return date;
    }

    /**
     * 获取限时急件时间
     *
     * @throws ParseException
     */
    public static String getLimitDispatchTime() {
        try {
            String dateTime = DateUtil.getCurrentServerTime();
            DateFormat datef = DateFormat.getDateTimeInstance();

            SimpleDateFormat m_DateFormatter = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm");
            Date sDate = datef.parse(dateTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sDate);
            cal.add(Calendar.HOUR, 2);
            Date eDate = cal.getTime();

            return m_DateFormatter.format(eDate);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 获取特急件时间
     *
     * @throws ParseException
     */

    public static String getEspecialDispatchTime() {
        try {
            String dateTime = DateUtil.getCurrentServerTime();
            DateFormat datef = DateFormat.getDateTimeInstance();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm");
            Date sDate = datef.parse(dateTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sDate);
            cal.add(Calendar.HOUR, 8);
            Date eDate = cal.getTime();
            String str = simpleDateFormat.format(eDate);

            return str;
        } catch (Exception e) {
            return null;
        }

    }

    // 获取时间到分
    public static String getTimeMinute() {
        try {
            String dateTime = DateUtil.getCurrentServerTime();
            DateFormat datef = DateFormat.getDateTimeInstance();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm");
            Date sDate = datef.parse(dateTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sDate);
            Date eDate = cal.getTime();

            return simpleDateFormat.format(eDate);
        } catch (Exception e) {
            return null;
        }

    }


    public static boolean isYesterdayORtoday(String newsCreatTime) {
        try {
            String today = DateUtil.getDate();
            newsCreatTime = DateUtil.getDateTime(newsCreatTime);
            String yesterday = getYesterday(newsCreatTime);
            if (today.equals(newsCreatTime) || today.equals(yesterday)) {
                return true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 获取上一天的信息
     *
     * @param nowDate format:2006-06-17
     * @return
     */
    public static String getYesterday(String nowDate) {
        String yesterday = "";
        int year = 0;
        int month = 0;
        int day = 0;
        try {
            year = Integer.parseInt(nowDate.substring(0, nowDate.indexOf("-")));
            month = Integer.parseInt(nowDate.substring(nowDate.indexOf("-") + 1, nowDate.lastIndexOf("-")));
            day = Integer.parseInt(nowDate.substring(nowDate.lastIndexOf("-") + 1));


            day = day + 1;
            if (day == 0) {
                month = month - 1;
                if (month == 0) {
                    //January
                    month = 12;
                    day = 31;
                    year = year - 1;
                } else {
                    //not Jan.
                    switch (month) {
                        //1|3|5|7|8|10|12) day=31;;
                        case 1:
                            day = 31;
                            break;
                        case 3:
                            day = 31;
                            break;
                        case 5:
                            day = 31;
                            break;
                        case 7:
                            day = 31;
                            break;
                        case 8:
                            day = 31;
                            break;
                        case 10:
                            day = 31;
                            break;
                        case 12:
                            day = 31;
                            break;
                        //4|6|9|11) day=30;;
                        case 4:
                            day = 30;
                            break;
                        case 6:
                            day = 30;
                            break;
                        case 9:
                            day = 30;
                            break;
                        case 11:
                            day = 30;
                            break;
                        case 2:
                            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                                //leap year
                                day = 29;
                            } else {
                                day = 28;
                            }
                    }
                }
            }

            String monthStr = "";
            String dayStr = "";

            if (month < 10) {
                monthStr = "0" + String.valueOf(month);
            } else {
                monthStr = String.valueOf(month);
            }

            if (day < 10) {
                dayStr = "0" + String.valueOf(day);
            } else {
                dayStr = String.valueOf(day);
            }

            yesterday = String.valueOf(year) + "-" + monthStr + "-" + dayStr;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return yesterday;
    }

    public static int getLastDateOfMonth(Date date) {
        int lastDate = 0;
        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(), date.getMonth() + 1, -1);
        lastDate = cal.get(Calendar.DATE);
        return lastDate + 1;
    }


    public static Date parseStringToDate(String usedate, String hour, String minute) {
        Date date = null;
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            date = sd.parse(usedate + " " + hour + ":" + minute);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return date;
    }

    /**
     * 返回中文日期
     *
     * @return String
     */
    public static String getGBYearMonth(String dateStr) {
        DateFormat df = DateFormat.getDateInstance();
        String gbdate = null;
        try {
            Date date = df.parse(dateStr + "-01");
            gbdate = DateFormatUtils.format(date, "yyyy年MM月");
        } catch (ParseException pe) {
        }
        return gbdate;
    }

    /**
     * 返回当前月第一天
     *
     * @return String
     */
    public static String getCurrentMonthFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return sdf.format(c.getTime());
    }

    /**
     * 返回当前月最后一天
     *
     * @return String
     */
    public static String getCurrentMonthLastDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sdf.format(c.getTime());
    }
//	public static void main(String[] args)throws Exception {
//		//System.out.println(getCurrentDate());
//	}

    /**
     * 通过开始结束时间计算中间相差的小时数
     *
     * @param type D 天 H 小时 M 分
     * @return
     */
    public static int getHours(String startTime, String endTime, String type) {
        if (startTime != null && !"".equals(startTime) && !"null".equals(startTime)
                && endTime != null && !"".equals(endTime) && !"null".equals(endTime)) {
            if (startTime.length() < 12) {
                startTime += " 00:00:00";
            }
            if (endTime.length() < 12) {
                endTime += " 00:00:00";
            }
            // 按照传入的格式生成一个simpledateformate对象
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
            long nh = 1000 * 60 * 60;// 一小时的毫秒数
            long nm = 1000 * 60;// 一分钟的毫秒数
            long ns = 1000;// 一秒钟的毫秒数
            long diff;
            long day = 0;
            long hour = 0;
            long min = 0;
            long sec = 0;
            // 获得两个时间的毫秒时间差异
            try {
                diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
                day = diff / nd;// 计算差多少天
                hour = diff % nd / nh + day * 24;// 计算差多少小时
                min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
                sec = diff % nd % nh % nm / ns;// 计算差多少秒
                // 输出结果
                System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"
                        + (min - day * 24 * 60) + "分钟" + sec + "秒。");
                System.out.println("hour=" + hour + ",min=" + min);

            } catch (ParseException e) {
                e.printStackTrace();
            }
            if ("h".equalsIgnoreCase(type)) {
                return (int) hour;
            } else {
                return (int) min;
            }
        } else {
            return 0;
        }
    }

    /**
     * 通过开始结束时间计算中间相差的时间数
     *
     * @param type D 天 H 小时 M 分
     * @return
     */
    public static int getDays(String startTime, String endTime, String type) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long diff;
        long day = 0;
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (int) day;
    }

    /**
     * @param date
     * @param day  想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
     * @return
     */
    public static Date getSomeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 通过开始结束时间计算中间相差的天数
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param type      D 天 H 小时 M 分
     * @return 天数
     */
    public static int getSeniorRepairDays(String startTime, String endTime, String type) {
        if (startTime != null && !"".equals(startTime) && !"null".equals(startTime)
                && endTime != null && !"".equals(endTime) && !"null".equals(endTime)) {
            // 按照传入的格式生成一个simpledateformate对象
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
            long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
            long diff;
            long day = 0;
            // 获得两个时间的毫秒时间差异
            try {
                diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
                day = diff / nd;// 计算差多少天
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return (int) day;
        } else {
            return 0;
        }

    }
}
