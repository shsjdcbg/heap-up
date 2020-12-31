package pers.dyx.fastjson.filter;

import com.alibaba.fastjson.serializer.PropertyFilter;

/**
 * PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化
 *
 * @author dyx
 * @date 2020/12/31 15:19
 */
public class PropertyFilterDemo implements PropertyFilter {
    @Override
    public boolean apply(Object source, String name, Object value) {
        if ("id".equals(name)) {
            int id = (Integer) value;
            return id <= 100;
        }
        return false;
    }
}
