package pers.dyx.limit.annotation;

import java.lang.annotation.*;

/**
 * 限流注解
 *
 * @author dyx
 * @date 2020/9/99:13
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Limit {

    /**
     * API key
     *
     * @return key
     */
    String key() default "";

    /**
     * 限制次数
     *
     * @return 限制次数
     */
    int limit() default 100;

    /**
     * 在多少秒最大地请求次数
     *
     * @return 在多少秒最大地请求次数
     */
    int second() default 60;
}
