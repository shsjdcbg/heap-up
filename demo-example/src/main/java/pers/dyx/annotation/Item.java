package pers.dyx.annotation;

import java.lang.annotation.*;

/**
 * Description：注解作用在方法上
 *
 * @author dyx
 * @date 2020/4/29 11:46
 */
@Target({ElementType.METHOD})
//注解的生命周期一直程序运行时都存在VM运行期间保留注解，可以通过反射机制读取注解信息
@Retention(RetentionPolicy.RUNTIME)
//注解包含在Javadoc中
@Documented
public @interface Item {
    String value();
}
