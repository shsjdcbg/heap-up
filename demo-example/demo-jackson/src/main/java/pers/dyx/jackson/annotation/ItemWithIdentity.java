package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * JsonIdentityInfo指出在序列化/反序列化值时应该使用对象标识，例如在处理无限递归类型的问题时。
 *
 * @author dyx
 * @date 2021/1/13 14:48
 */
@Data
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ItemWithIdentity {
    private int id;
    private String itemName;
    private UserWithIdentity owner;
}
