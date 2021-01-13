package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JacksonInject;
import lombok.Data;

/**
 * JacksonInject表示属性将从注入而不是从JSON数据获取其值。
 * 应用场景： 假设json字段有一些缺少的属性，抓换成实体类的时候没有的属性将为null，但是我们在某些需求当中需要将为null的属性都设置为默认值，
 * 这时候我们就可以用到这个注解了，它的功能就是在反序列化的时候将没有的字段设置为我们设置好的默认值。
 *
 * @author dyx
 * @date 2021/1/8 16:26
 */
@Data
public class BeanWithInject {
    @JacksonInject
    private int id;

    private String name;
}
