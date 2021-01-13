package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

/**
 * JsonAlias在反序列化期间为属性定义一个或多个可选名称。
 *
 * @author dyx
 * @date 2021/1/8 16:39
 */
@Data
public class AliasBean {

    @JsonAlias({"fName", "f_name"})
    private String firstName;
    private String lastName;

}
