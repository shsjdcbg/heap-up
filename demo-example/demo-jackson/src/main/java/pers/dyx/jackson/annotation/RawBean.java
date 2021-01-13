package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsonRawValue注释可以指示Jackson按原样序列化属性。 直接显示属性值，对字符串，即是去掉双引号。
 *
 * @author dyx
 * @date 2021/1/8 16:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RawBean {
    private String name;

    @JsonRawValue
    private String json;
}
