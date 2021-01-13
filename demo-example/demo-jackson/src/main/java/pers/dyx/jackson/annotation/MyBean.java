package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JsonGetter注释是@JsonProperty注释的替代方法，它将方法标记为getter方法。
 *
 * @author dyx
 * @date 2021/1/8 15:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"n", "id"})
public class MyBean {
    private int id;
    private String name;

    @JsonGetter("n")
    public String getName() {
        return name;
    }

    @JsonSetter("n")
    public void setName(String name) {
        this.name = name;
    }
}
