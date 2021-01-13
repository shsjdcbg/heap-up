package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pers.dyx.jackson.annotation.deserializer.CustomDateDeserializer;
import pers.dyx.jackson.annotation.serializer.CustomDateSerializer;

import java.util.Date;

/**
 * JsonSerialize表示在封送实体时要使用的自定义序列化程序。
 * JsonDeserialize指示使用自定义反序列化程序。
 *
 * @author dyx
 * @date 2021/1/8 16:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventWithSerializer {

    private String name;

    @JsonSerialize(using = CustomDateSerializer.class)
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date eventDate;
}
