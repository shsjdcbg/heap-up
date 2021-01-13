package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * JsonAnyGetter注解允许灵活地将 Map 字段用作标准属性。
 * 应用场景：主要用来获取序列化时未匹配上的字段。
 * 注意事项：
 * （1）用于非静态无参方法，方法名可以随意；
 * （2）方法返回值必须是Map类型；
 * （3）在一个实体类中仅仅用在一个方法上；
 * （4）序列化的时候json字段的key就是返回Map的key，value就是Map的value。
 * <p>
 * <p>
 * JsonAnySetter允许我们灵活地使用Map作为标准属性。
 * 应用场景：主要用于反序列化时，JSON中的属性将简单地添加到映射中，一般将对应不上的字段全部放到Map里面。
 * 注意事项：
 * （1）用于非静态无参方法，注解的方法必须有两个参数，第一个是json字段中的key，第二个是value，方法名随意；
 * （2）也可以用在Map对象属性上面。
 *
 * @author dyx
 * @date 2021/1/8 15:46
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ExtendableBean {
    @NonNull
    private String name;
    private Map<String, String> properties;

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    @JsonAnySetter
    public void add(String v1, String v2) {
        if (properties == null) {
            properties = new HashMap<>();
        }
        properties.put(v1, v2);
    }

}
