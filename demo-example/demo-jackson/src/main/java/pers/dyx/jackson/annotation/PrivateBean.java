package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * JsonAutoDetect可以覆盖哪些属性可见哪些属性不可见的默认语义。
 *
 * @author dyx
 * @date 2021/1/13 14:02
 */
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PrivateBean {
    private int id;
    private String name;
}
