package pers.dyx.fastjson.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dyx
 * @date 2020/12/31 17:17
 */
@Data
public class Company {
    private int code;
    @JSONField(serialzeFeatures = SerializerFeature.BeanToArray, parseFeatures = Feature.SupportArrayToBean)
    private List<Department> departments = new ArrayList<Department>();
}
