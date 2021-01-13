package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsonUnwrapped定义序列化/反序列化时应展开/展平的值。
 *
 * @author dyx
 * @date 2021/1/13 14:24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnwrappedUser {

    private int id;

    @JsonUnwrapped
    private Name name;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Name {
        private String firstName;
        private String lastName;
    }
}
