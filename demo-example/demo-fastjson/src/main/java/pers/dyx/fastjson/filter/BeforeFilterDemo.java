package pers.dyx.fastjson.filter;

import com.alibaba.fastjson.serializer.BeforeFilter;
import pers.dyx.fastjson.vo.User;

import java.util.Date;

/**
 * BeforeFilter 序列化时在最前添加内容
 *
 * @author dyx
 * @date 2020/12/31 15:33
 */
public class BeforeFilterDemo extends BeforeFilter {
    @Override
    public void writeBefore(Object object) {
        User user = (User) object;
        user.setDate(new Date());
        writeKeyValue("utime", new Date());
    }
}
