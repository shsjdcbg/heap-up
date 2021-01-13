package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * JsonIgnoreType将注释类型的所有属性标记为忽略。
 *
 * @author dyx
 * @date 2021/1/13 13:55
 */
@Data
@AllArgsConstructor
public class User {

    private int id;
    private Name name;

    @Data
    @AllArgsConstructor
    @JsonIgnoreType
    public static class Name {
        private String firstName;
        private String lastName;
    }
}
