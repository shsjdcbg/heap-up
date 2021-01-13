package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsonView指示将在其中包含属性以进行序列化/反序列化的视图。
 *
 * @author dyx
 * @date 2021/1/13 14:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @JsonView(Views.Public.class)
    private int id;

    @JsonView(Views.Public.class)
    private String itemName;

    @JsonView(Views.Internal.class)
    private String ownerName;
}
