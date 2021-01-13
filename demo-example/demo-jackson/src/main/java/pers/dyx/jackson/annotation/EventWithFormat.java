package pers.dyx.jackson.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * JsonFormat注释指定序列化日期/时间值时的格式。
 *
 * @author dyx
 * @date 2021/1/13 14:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventWithFormat {
    private String name;

    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd hh:mm:ss")
    private Date eventDate;
}
