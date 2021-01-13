package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsonIgnoreProperties是一个类级别的注释，用于标记Jackson将忽略的属性或属性列表。
 * JsonIgnore注释用于在字段级别标记要忽略的属性。
 *
 * @author dyx
 * @date 2021/1/8 16:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"id"})
public class BeanWithIgnore {
    @JsonIgnore
    private int id;
    private String name;
}
