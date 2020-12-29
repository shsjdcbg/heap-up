package pers.dyx.tool.javafaker;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * 造数据用
 *
 * @author dyx
 * @date 2020/12/29 11:00
 */
public class UserInfo {
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 手机
     */
    private String cellPhone;
    /**
     * 大学
     */
    private String universityName;
    /**
     * 城市
     */
    private String city;
    /**
     * 地址
     */
    private String street;

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "realName='" + realName + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", universityName='" + universityName + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }



    public static void main(String[] args) {
        Faker faker = new Faker(Locale.CHINA);
        for (int i = 0; i < 10; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setRealName(faker.name().fullName());
            userInfo.setCellPhone(faker.phoneNumber().cellPhone());
            userInfo.setCity(faker.address().city());
            userInfo.setStreet(faker.address().streetAddress());
            userInfo.setUniversityName(faker.university().name());
            System.out.println(userInfo);
        }
    }
}
