package pers.dyx.limit.entity;

import pers.dyx.limit.constant.ResultType;

/**
 * 限流结果实体类
 *
 * @author dyx
 * @date 2020/9/9 10:23
 */
public class LimitResult {

    private String url;

    private ResultType resultType;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ResultType getResultType() {
        return resultType;
    }

    public void setResultType(ResultType resultType) {
        this.resultType = resultType;
    }

    @Override
    public String toString() {
        return "LimitResult{" +
                "url='" + url + '\'' +
                ", resultType=" + resultType +
                '}';
    }
}
