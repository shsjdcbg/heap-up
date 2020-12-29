package pers.dyx.limit.entity;

/**
 * @author dyx
 * @date 2020/9/9 10:06
 */
public class LimitEntity {

    /**
     * API key
     */
    private String key;

    /**
     * 限制次数
     */
    private int limit;

    /**
     * 在多少秒最大地请求次数
     */
    private int second;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "LimitEntity{" +
                "key='" + key + '\'' +
                ", limit=" + limit +
                ", second=" + second +
                '}';
    }
}
