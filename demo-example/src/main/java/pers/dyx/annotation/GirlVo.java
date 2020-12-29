package pers.dyx.annotation;

import java.io.Serializable;

/**
 * Description：
 *
 * @author dyx
 * @date 2020/4/29 11:48
 */
public class GirlVo implements Serializable {

    private static final long serialVersionUID = -8545755852948661858L;
    /**
     * ID号
     */
    private String girlId;
    /**
     * 姓名
     */
    private String girlName;
    /**
     * 年龄
     */
    private Long age;
    /**
     * 身高
     */
    private Double height;
    /**
     * 罩杯
     */
    private String cupSize;

    public String getGirlId() {
        return girlId;
    }

    public void setGirlId(String girlId) {
        this.girlId = girlId;
    }

    public String getGirlName() {
        return girlName;
    }

    public void setGirlName(String girlName) {
        this.girlName = girlName;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    @Override
    public String toString() {

        return "GirlVo [girlId=" + girlId
                + ",girlName=" + girlName
                + ",age=" + age
                + ",height=" + height
                + ",cupSize=" + cupSize
                + "]";
    }
}