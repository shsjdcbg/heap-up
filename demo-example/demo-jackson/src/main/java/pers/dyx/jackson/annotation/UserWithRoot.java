package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 如果启用了包装，则使用@JsonRootName注解来指定要使用的根包装的名称。
 *
 * @author dyx
 * @date 2021/1/8 16:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value = "user")
public class UserWithRoot {
    private int id;
    private String name;
}
