package pers.dyx.fastjson.vo;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 定制序列化
 * 通过@JSONType定制序列化
 *
 * @author dyx
 * @date 2020/12/31 14:35
 */
@Data
@AllArgsConstructor
@JSONType(orders = {"id", "date"}, includes = {"id", "date"})
public class User2 {

    private int id;

    private Date date;

    private String name;
}
