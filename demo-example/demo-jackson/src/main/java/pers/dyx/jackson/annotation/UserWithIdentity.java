package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * JsonIdentityInfo指出在序列化/反序列化值时应该使用对象标识，例如在处理无限递归类型的问题时。
 *
 * @author dyx
 * @date 2021/1/13 14:48
 */
@Data
@RequiredArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserWithIdentity {
    @NonNull
    private int id;
    @NonNull
    private String name;
    private List<ItemWithIdentity> userItems;

    public void addItem(ItemWithIdentity itemWithIdentity) {
        if (userItems == null) {
            userItems = new ArrayList<>();
        }
        userItems.add(itemWithIdentity);
    }
}
