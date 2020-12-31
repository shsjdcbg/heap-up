package pers.dyx.fastjson.filter;

import com.alibaba.fastjson.serializer.AfterFilter;
import pers.dyx.fastjson.vo.User;

import java.util.Date;

/**
 * @author dyx
 * @date 2020/12/31 15:38
 */
public class AfterFilterDemo extends AfterFilter {
    @Override
    public void writeAfter(Object object) {
        User user = (User) object;
        user.setDate(new Date());
        writeKeyValue("utime", new Date());
    }
}
