package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 我们可以使用@JsonPropertyOrder注释来指定序列化的属性顺序。
 *
 * @author dyx
 * @date 2021/1/8 16:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"name", "id"})
public class MyBean1 {
    private int id;
    private String name;
}
