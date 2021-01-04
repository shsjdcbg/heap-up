package pers.dyx.utils;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: dyx
 * @date: 2018/11/22 14:19
 * @description:
 */
public class StringUtils {
    /**
     * 判断给定字符串是否为空 或者为空字符串
     *
     * @param str
     * @return true表示空， false表示不为空
     */
    public static boolean isEmpty(String str) {
        return (str == null || "".equals(str) || "null".equals(str));
    }

    /**
     * 判断给定字符串是否不为空 或者不为空字符串
     *
     * @param str
     * @return fasle表示空， true表示不为空
     */
    public static boolean isNotEmpty(String str) {
        return (str != null && !"".equals(str) && !"null".equals(str));
    }


    /**
     * 判断字符串对象与给定值是否相等
     * 都为空表示相等
     *
     * @param StringObject 字符串对象
     * @param value        给定值
     * @return 相等返回true，不相等返回false
     */
    public static boolean isEqual(String StringObject, String value) {
        if ((StringObject == null && value == null) || (isNotEmpty(StringObject) && StringObject.equals(value))) {
            return true;
        }
        return false;
    }

    /**
     * 截取输入空格
     *
     * @param str 字符串
     * @return
     */
    public static String getTrim(String str) {
        if (str != null) {
            return str.trim();
        }
        return null;
    }

    /**
     * 判断是否符合匹配
     *
     * @param reg   正则表达式
     * @param param 字符串
     * @return
     */
    public static boolean match(String reg, String param) {
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(param);
        return m.matches();
    }

    /**
     * 判断String字符串是否为数字
     *
     * @param str 字符串
     * @return 是否为数字
     */
    public static boolean isInt(String str) {
        //验证是否为空
        if (isEmpty(str)) {
            return false;
        }
        //使用正则表达式判断该字符串是否为数字，第一个\是转义符，\d+表示匹配1个或 //多个连续数字，"+"和"*"类似，"*"表示0个或多个
        return match("\\d+", str);
    }

    public static String stringSplit(String orgStr) {
        String tmp = orgStr;
        String splitStr = null;
        while (true) {
            int j = tmp.indexOf(",");
            if (j < 0) {
                break;
            }
            splitStr = orgStr.substring(0, j);
            tmp = orgStr.substring(j + 1);
        }
        tmp = orgStr;
        return tmp;
    }

    /**
     * 检查给定的CharSequence既不是{@code null}也不是长度0。
     * 注意：对于纯粹由空格组成的CharSequence，将返回{@code true}。
     * StringUtils.hasLength(null) = false
     * StringUtils.hasLength("") = false
     * StringUtils.hasLength(" ") = true
     * StringUtils.hasLength("Hello") = true
     *
     * @param str 要检查的CharSequence (可以为 {@code null})
     * @return {@code true}如果CharSequence不为null并且具有长度
     */
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * 检查给定的字符串既不是{@code null}也不是长度0。
     * 注意：对于纯粹由空格组成的字符串，将返回{@code true}。
     *
     * @param str 要检查的CharSequence (可以为 {@code null})
     * @return {@code true}如果CharSequence不为null并且具有长度
     */
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    /**
     * 检查给定的CharSequence是否具有实际文本。
     * 更具体地说，如果字符串不是{@code null}，则返回{@code true}，
     * 其长度大于0，并且至少包含一个非空白字符。
     * StringUtils.hasText(null) = false
     * StringUtils.hasText("") = false
     * StringUtils.hasText(" ") = false
     * StringUtils.hasText("12345") = true
     * StringUtils.hasText(" 12345 ") = true
     *
     * @param str 字符串
     * @return 如果字符串不是{@code null}，则其长度为大于0，并且不只包含空格
     */
    public static boolean hasText(CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 检查给定的字符串是否具有实际文本。
     *
     * @param str 字符串
     * @return 如果字符串不是{@code null}，则其长度为大于0，并且不只包含空格
     */
    public static boolean hasText(String str) {
        return hasText((CharSequence) str);
    }

    /**
     * 将给定的Collection复制到String数组中。
     * 集合只能包含String元素。
     *
     * @param collection 要复制的集合
     * @return String数组（如果传入的集合为{@code null}，则为{@code null}）
     */
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }
        return collection.toArray(new String[collection.size()]);
    }

}
