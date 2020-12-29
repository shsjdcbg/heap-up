package pers.dyx;

import lombok.Builder;
import lombok.ToString;

/**
 * @author dyx
 * @date 2020/12/29 16:04
 */
@Builder
@ToString
public class BuilderExample {
    private Long id;
    private String name;
    private Integer age;

    public static void main(String[] args) {
        BuilderExample example = BuilderExample.builder()
                .id(1L)
                .name("test")
                .age(20)
                .build();
        System.out.println(example);
    }
}
