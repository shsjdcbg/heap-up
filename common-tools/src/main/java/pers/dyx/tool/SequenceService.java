package pers.dyx.tool;

import pers.dyx.util.Assert;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 顺序生成编号demo
 */
public class SequenceService {

    private Map<String, SequenceService.A> vMap = new HashMap();

    private long cache = 50L;

    public long getCache() {
        return cache;
    }

    public void setCache(long cache) {
        this.cache = cache;
    }

    /**
     * 生成下一个编号
     *
     * @param stype  编号类型
     * @param length 编号固定长度
     * @param prefix 编号前缀
     * @return 编号字符串
     */
    public String nextValue(String stype, int length, String prefix) {
        Assert.isTrue(length > 0, "长度不能小于0");
        long t = this.nextValue(stype);
        String valueStr = String.valueOf(t);
        Assert.isTrue(valueStr.length() <= length, String.format("长度太短,当前值已经到%d,指定的长度为%d", t, length));
        int prefixLen = prefix == null ? 0 : prefix.length();
        StringBuilder sb = new StringBuilder(length + prefixLen);
        if (StringUtils.isNotEmpty(prefix)) {
            sb.append(prefix);
        }

        sb.append(fill(valueStr, '0', length, true));
        return sb.toString();
    }

    public static String fill(String str, char filledChar, int len, boolean isPre) {
        int strLen = str.length();
        if (strLen > len) {
            return str;
        } else {
            String filledStr = repeat(filledChar, len - strLen);
            return isPre ? filledStr.concat(str) : str.concat(filledStr);
        }
    }

    public static String repeat(char c, int count) {
        if (count <= 0) {
            return "";
        } else {
            char[] result = new char[count];

            for (int i = 0; i < count; ++i) {
                result[i] = c;
            }

            return new String(result);
        }
    }

    private long nextValue(String stype) {
        SequenceService.A a = null;
        synchronized (this) {
            a = this.vMap.get(stype);
            if (a == null) {
                a = new SequenceService.A();
                this.vMap.put(stype, a);
                a.max = -1;
                a.cur = 0L;
            }
        }

        synchronized (a) {
            if (a.cur <= a.max) {
                return (long) (a.cur++);
            } else {
                this.setCache(this.getCache());
                // TODO
                //更新序列号表 update umc_sequence set id=id+#{cache},update_time=#{utime} where stype=#{stype}
                //如果更新数量为0 插入insert into umc_sequence (stype, sname, id,update_time)
                //        values (#{stype,jdbcType=VARCHAR}, #{sname,jdbcType=VARCHAR}, #{cache,jdbcType=BIGINT},#{updateTime,jdbcType=BIGINT})
                // value 为 查询SELECT id FROM umc_sequence WHERE stype = #{stype}
                long value = 50L;
                a.cur = value - this.getCache() + 1L;
                a.max = value;
                return (long) (a.cur++);
            }
        }
    }


    private class A {
        private long cur;
        private long max;

        public A() {
            this.cur = 0L;
            this.max = 0L;
        }
    }
}
