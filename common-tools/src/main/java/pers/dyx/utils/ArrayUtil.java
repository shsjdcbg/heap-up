package pers.dyx.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数组工具类
 *
 * @author dyx
 * @date 2020/9/310:11
 */
public class ArrayUtil {

    /**
     * 去掉数组中空项
     *
     * @param olds 需要去掉空项的数组
     * @return 去掉空项的数组
     */
    public static String[] trim(String[] olds) {
        if (olds == null || olds.length == 0) {
            return olds;
        }
        List<String> list = new ArrayList<>();
        for (String old : olds) {
            if (old != null && !"".equals(old)) {
                list.add(old);
            }
        }
        String[] news = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            news[i] = list.get(i);
        }
        return news;
    }

    /**
     * 匹配字符出现次数
     *
     * @param srcText  字符串
     * @param findText 匹配字符串
     * @return 次数
     */
    public static int appearNumber(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }
}
