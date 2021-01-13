package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 我们可以使用@JsonInclude排除具有 empty/null/默认值的属性。
 *
 * @author dyx
 * @date 2021/1/8 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyBean3 {
    private int id;
    private String name;
}
