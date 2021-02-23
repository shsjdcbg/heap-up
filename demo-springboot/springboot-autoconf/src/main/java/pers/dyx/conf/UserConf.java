package pers.dyx.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 用户配置
 *
 * @author dyx
 * @date 2020/10/29 16:48
 */
@Component
public class UserConf {

    //美元表达式(从配置文件、系统环境变量读取)
    @Value("${conf.test.string}")
    private String stringTest;

    @Value("${conf.test.integer}")
    private Integer integerTest;

    @Value("${conf.test.boolean}")
    private Boolean booleanTest;

    // 以默认值的形式赋予值， @Value默认必须要有配置项，配置项可以为空，但是必须要有，如果没有配置项，则可以给默认值
    @Value("${conf.test.string:这是默认值}")
    private String stringTestDefault;

    @Value("${conf.test.integer:7900}")
    private String integerTestDefault;

    @Value("${conf.test.boolean:false}")
    private Boolean booleanTestDefault;

    //EL表达式
    @Value("#{11*2}")
    private Integer age;

    //字面量
    @Value("true")
    private Boolean boss;

    public String getStringTest() {
        return stringTest;
    }

    public void setStringTest(String stringTest) {
        this.stringTest = stringTest;
    }

    public Integer getIntegerTest() {
        return integerTest;
    }

    public void setIntegerTest(Integer integerTest) {
        this.integerTest = integerTest;
    }

    public Boolean getBooleanTest() {
        return booleanTest;
    }

    public void setBooleanTest(Boolean booleanTest) {
        this.booleanTest = booleanTest;
    }

    public String getStringTestDefault() {
        return stringTestDefault;
    }

    public void setStringTestDefault(String stringTestDefault) {
        this.stringTestDefault = stringTestDefault;
    }
}
