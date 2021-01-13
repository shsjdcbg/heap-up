package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * JsonManagedReference和@JsonBackReference注释可以处理父/子关系并绕过循环。
 * 这两个标注通常配对使用，通常用在父子关系中。
 * JsonBackReference标注的属性在序列化时，会被忽略（即结果中的json数据不包含该属性的内容）。
 * JsonManagedReference标注的属性则会被序列化。
 * 在序列化时，@JsonBackReference的作用相当于@JsonIgnore，此时可以没有@JsonManagedReference。
 * 但在反序列化时，如果没有@JsonManagedReference，则不会自动注入@JsonBackReference标注的属性（被忽略的父或子）；
 * 如果有@JsonManagedReference，则会自动注入自动注入@JsonBackReference标注的属性。
 *
 * @author dyx
 * @date 2021/1/13 14:38
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class UserWithRef {
    @NonNull
    private int id;
    @NonNull
    private String name;

    @JsonBackReference
    private List<ItemWithRef> userItems;

    public void addItem(ItemWithRef itemWithRef) {
        if (userItems == null) {
            userItems = new ArrayList<>();
        }
        userItems.add(itemWithRef);
    }
}
