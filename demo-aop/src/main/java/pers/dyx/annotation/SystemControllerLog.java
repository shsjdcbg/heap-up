package pers.dyx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 拦截controller
 *
 * @author dyx
 * @date 2020/9/14 14:05
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SystemControllerLog {

    /**
     * 描述业务操作
     *
     * @return 字符串
     */
    String description() default "";
}
