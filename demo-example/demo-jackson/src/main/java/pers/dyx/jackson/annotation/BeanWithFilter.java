package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsonFilter注释指定序列化期间要使用的过滤器。
 *
 * @author dyx
 * @date 2021/1/13 14:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("myFilter")
public class BeanWithFilter {
    private int id;
    private String name;
}
