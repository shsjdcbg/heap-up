package pers.dyx.fastjson.parseprocess;

import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;

import java.lang.reflect.Type;

/**
 * @author dyx
 * @date 2020/12/31 15:50
 */
public class ExtraTypeProviderDemo extends ExtraProcessorDemo implements ExtraTypeProvider {
    @Override
    public Type getExtraType(Object object, String key) {
        if ("value".equals(key)) {
            return double.class;
        }
        return null;
    }
}
