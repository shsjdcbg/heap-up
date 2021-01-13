package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsonTypeInfo指示序列化中要包含的类型信息的详细信息
 * JsonSubTypes表示带注释类型的子类型
 * JsonTypeName定义用于注释类的逻辑类型名称
 *
 * @author dyx
 * @date 2021/1/13 14:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zoo {
    private Animal animal;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
    @JsonSubTypes({@JsonSubTypes.Type(value = Dog.class, name = "dog"), @JsonSubTypes.Type(value = Cat.class, name = "cat")})
    public static class Animal {
        private String name;
    }

    @Data
    @NoArgsConstructor
    @JsonTypeName("dog")
    public static class Dog extends Animal {
        private double barkVolume;

        public Dog(final String name) {
            super(name);
        }
    }

    @Data
    @NoArgsConstructor
    @JsonTypeName("cat")
    public static class Cat extends Animal {
        private boolean likesCream;
        private int lives;

        public Cat(final String name) {
            super(name);
        }
    }
}
