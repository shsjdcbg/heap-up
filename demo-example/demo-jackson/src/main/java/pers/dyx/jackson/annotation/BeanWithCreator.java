package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 在反序列化时，Jackson默认会调用对象的无参构造函数，如果我们不定义任何构造函数，Jvm会负责生成默认的无参构造函数。
 * 但是如果我们定义了构造函数，并且没有提供无参构造函数时，Jackson会报错。
 * JsonCreator注解用在对象的反序列时指定特定的构造函数或者工厂方法。如果默认构造函数无法满足需求，或者说我们需要在构造对象时做一些特殊逻辑，可以使用该注解。
 * 该注解需要搭配@JsonProperty使用。
 *
 * @author dyx
 * @date 2021/1/8 16:23
 */
@Data
public class BeanWithCreator {
    private int id;
    private String name;

    @JsonCreator
    public BeanWithCreator(
            @JsonProperty("id") int id,
            @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }
}
