package pers.dyx.fastjson.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import pers.dyx.fastjson.serializer.ModelMoneySerializer;

import java.util.Date;

/**
 * 定制序列化
 * 通过@JSONField定制序列化
 * ordinal：配置序列化和反序列化的顺序，1.1.42版本之后才支持
 * name：指定字段的名称
 * format：指定字段的格式，对日期格式有用
 * serialize：是否序列化
 * deserialize：是否反序列化
 * serializeUsing：在fastjson 1.2.16版本之后，JSONField支持新的定制化配置serializeUsing，可以单独对某一个类的某个属性定制序列化
 *
 * @author dyx
 * @date 2020/12/31 14:35
 */
@Data
@AllArgsConstructor
public class User1 {

    @JSONField(name = "ID")
    private int id;

    /**
     * 配置date序列化和反序列使用yyyy-MM-dd日期格式
     */
    @JSONField(name = "birthday", format = "yyyy-MM-dd")
    private Date date;

    /**
     * 使用serialize/deserialize指定字段序列化/反序列化
     */
    @JSONField(serialize = false)
    private String name;

    @JSONField(serializeUsing = ModelMoneySerializer.class)
    private int money;


}
