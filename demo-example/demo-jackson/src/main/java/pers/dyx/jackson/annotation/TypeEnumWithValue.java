package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * JsonValue注解用于序列化整个实例的单个方法。可以用在get方法或者属性字段上，一个类只能用一个，当加上@JsonValue注解是，序列化是只返回这一个字段的值。
 *
 * @author dyx
 * @date 2021/1/8 16:12
 */
public enum TypeEnumWithValue {
    TYPE1(1, "Type A"),
    TYPE2(2, "Type 2");

    private Integer id;
    private String name;

    TypeEnumWithValue(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
