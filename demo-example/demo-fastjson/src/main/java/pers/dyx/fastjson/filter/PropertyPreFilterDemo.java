package pers.dyx.fastjson.filter;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;

/**
 * PropertyPreFilter 根据PropertyName判断是否序列化
 *
 * @author dyx
 * @date 2020/12/31 15:23
 */
public class PropertyPreFilterDemo implements PropertyPreFilter {
    @Override
    public boolean apply(JSONSerializer jsonSerializer, Object o, String name) {
        return !"id".equals(name);
    }
}
