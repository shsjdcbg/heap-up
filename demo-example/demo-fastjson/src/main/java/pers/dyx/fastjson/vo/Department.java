package pers.dyx.fastjson.vo;

import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dyx
 * @date 2020/12/31 17:18
 */
@Data
@AllArgsConstructor
@JSONType(serialzeFeatures = SerializerFeature.BeanToArray, parseFeatures = Feature.SupportArrayToBean)
public class Department {
    private int id;
    private String name;

}
