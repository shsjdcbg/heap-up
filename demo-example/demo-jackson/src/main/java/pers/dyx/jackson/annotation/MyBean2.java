package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsonGetter注解是@JsonProperty注解的替代方法，标记为getter方法上。
 * JsonSetter注解是@JsonProperty注解的替代方法，标记为setter方法上。
 *
 * @author dyx
 * @date 2021/1/8 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyBean2 {
    private int id;
    @JsonProperty("n")
    private String name;
}
