package pers.dyx.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import pers.dyx.fastjson.filter.*;
import pers.dyx.fastjson.parseprocess.ExtraProcessorDemo;
import pers.dyx.fastjson.parseprocess.ExtraTypeProviderDemo;
import pers.dyx.fastjson.vo.User;
import pers.dyx.fastjson.vo.User1;
import pers.dyx.fastjson.vo.User2;
import pers.dyx.fastjson.vo.User3;

import java.util.Date;
import java.util.List;

/**
 * @author dyx
 * @date 2020/12/31 14:35
 */
public class FastJsonDemo {

    public static void main(String[] args) {
        User user = new User(1, new Date(), 1024);
        //序列化
        System.out.println(JSON.toJSONString(user));
        //反序列化
        System.out.println(JSON.parseObject("{id:'32',date:'2020-12-31 12:00:00'}", User.class));
        // 泛型
        List<User> list = JSON.parseObject("[{id:'32',date:'2020-12-31 12:00:00'},{id:'33',date:'2020-12-31 12:00:00'}]", new TypeReference<List<User>>() {
        });
        System.out.println(list);

        //处理日期
        System.out.println(JSON.toJSONStringWithDateFormat(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));


        // 通过@JSONField定制序列化
        User1 user1 = new User1(1, new Date(), "张三", 1024);
        //序列化
        System.out.println(JSON.toJSONString(user1));
        //反序列化
        System.out.println(JSON.parseObject("{ID:'32',birthday:'2020-12-31 12:00:00',money:'1024'}", User1.class));


        // 通过@JSONType定制序列化
        User2 user2 = new User2(1, new Date(), "张三");
        //序列化
        System.out.println(JSON.toJSONString(user2));
        //反序列化
        System.out.println(JSON.parseObject("{id:'32',date:'2020-12-31 12:00:00'}", User2.class));


        // 通过SerializeFilter定制序列化
        //      (1)PropertyPreFilter 根据PropertyName判断是否序列化
        System.out.println(JSON.toJSONString(user, new PropertyPreFilterDemo()));
        //      (2)PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化
        System.out.println(JSON.toJSONString(user, new PropertyFilterDemo()));
        //      (3)NameFilter 序列化时修改Key。fastjson内置一个PascalNameFilter，用于输出将首字符大写的Pascal风格。
        System.out.println(JSON.toJSONString(user, new PascalNameFilter()));
        //      （4）ValueFilter 序列化时修改Value
        System.out.println(JSON.toJSONString(user, new ValueFilterDemo()));
        //      (5)BeforeFilter 序列化时在最前添加内容
        System.out.println(JSON.toJSONString(user, new BeforeFilterDemo()));
        //      (6)AfterFilter 序列化时在最后添加内容
        System.out.println(JSON.toJSONString(user, new AfterFilterDemo()));


        // 通过ParseProcess定制反序列化，要有无参构造函数
        //      (1)使用ExtraProcessor 处理多余字段，value为多余的字段
        System.out.println(JSON.parseObject("{id:'32',date:'2020-12-31 12:00:00',money:'1024',name:'34978'}", User3.class, new ExtraProcessorDemo()));
        //      (2)使用ExtraTypeProvider 为多余的字段提供类型
        System.out.println(JSON.parseObject("{id:'32',date:'2020-12-31 12:00:00',money:'1024',value:'34978'}", User3.class, new ExtraTypeProviderDemo()));


    }
}
