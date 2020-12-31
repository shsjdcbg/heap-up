package pers.dyx.fastjson.filter;

import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * ValueFilter 序列化时修改Value
 *
 * @author dyx
 * @date 2020/12/31 15:27
 */
public class ValueFilterDemo implements ValueFilter {
    @Override
    public Object process(Object source, String name, Object value) {
        if ("money".equals(name)) {
            double money = Double.parseDouble(String.valueOf(value));
            return money / 100;
        }
        return null;
    }
}
