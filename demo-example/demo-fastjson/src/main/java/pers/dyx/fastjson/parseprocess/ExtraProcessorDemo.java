package pers.dyx.fastjson.parseprocess;

import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import pers.dyx.fastjson.vo.User3;

/**
 * @author dyx
 * @date 2020/12/31 15:41
 */
public class ExtraProcessorDemo implements ExtraProcessor {

    @Override
    public void processExtra(Object object, String key, Object value) {
        if (object != null) {
            User3 user3 = (User3) object;
            user3.getAttributes().put(key, value);
        }
    }
}
