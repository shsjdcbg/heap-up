package pers.dyx.fastjson;

import com.alibaba.fastjson.JSONPath;
import pers.dyx.fastjson.vo.Entity;

import java.util.*;

/**
 * fastjson 1.2.0之后的版本支持JSONPath。这是一个很强大的功能，可以在java框架中当作对象查询语言（OQL）来使用。
 * https://github.com/alibaba/fastjson/wiki/JSONPath
 *
 * @author dyx
 * @date 2020/12/31 16:56
 */
public class JsonPathDemo {

    public static void main(String[] args) {
        Entity entity = new Entity(123, new Object());

        //求值
        System.out.println(JSONPath.eval(entity, "$.id"));
        //是否包含，path中是否存在对象
        System.out.println(JSONPath.contains(entity, "$.value"));
        //是否包含，path中是否存在指定值，如果是集合或者数组，在集合中查找value是否存在
        System.out.println(JSONPath.containsValue(entity, "$.id", 123));
        System.out.println(JSONPath.containsValue(entity, "$.value", entity.getValue()));
        //计算Size，Map非空元素个数，对象非空元素个数，Collection的Size，数组的长度。其他无法求值返回-1
        System.out.println(JSONPath.size(entity, "$"));


        // 读取集合多个元素的某个属性
        List<Entity> entities = new ArrayList<>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        // 返回enties的所有名称
        List<String> names = (List<String>) JSONPath.eval(entities, "$.name");
        System.out.println(names);


        //返回集合中多个元素
        entities = new ArrayList<>();
        entities.add(new Entity("wenshao"));
        entities.add(new Entity("ljw2083"));
        entities.add(new Entity("Yako"));
        // 返回下标为1和2的元素
        List<Entity> result = (List<Entity>) JSONPath.eval(entities, "[1,2]");
        System.out.println(result);


        //按范围返回集合的子集
        // 返回下标从0到2的元素
        result = (List<Entity>) JSONPath.eval(entities, "[0:2]");
        System.out.println(result);


        //通过条件过滤，返回集合的子集
        entities = new ArrayList<Entity>();
        entities.add(new Entity(1001, "ljw2083"));
        entities.add(new Entity(1002, "wenshao"));
        entities.add(new Entity(1003, "yakolee"));
        entities.add(new Entity(1004, null));
        result = (List<Entity>) JSONPath.eval(entities, "[id in (1001,1002)]");
        System.out.println(result);


        //根据属性值过滤条件判断是否返回对象，修改对象，数组属性添加元素
        entity = new Entity(1001, "ljw2083");
        System.out.println(JSONPath.eval(entity, "[id = 1001]"));
        System.out.println(JSONPath.eval(entity, "[id = 1002]"));
        //将id字段修改为123456
        JSONPath.set(entity, "id", 123456);
        System.out.println(entity.getId().intValue());
        //将value字段赋值为长度为0的数组
        JSONPath.set(entity, "value", new int[0]);
        //将value字段的数组添加元素1,2,3
        JSONPath.arrayAdd(entity, "value", 1, 2, 3);


        Map root = Collections.singletonMap("company",
                Collections.singletonMap("departs",
                        Arrays.asList(
                                Collections.singletonMap("id", 1001),
                                Collections.singletonMap("id", 1002),
                                Collections.singletonMap("id", 1003)
                        )
                ));

        List<Object> ids = (List<Object>) JSONPath.eval(root, "$..id");
        System.out.println(ids);


        //使用keySet抽取对象的属性名，null值属性的名字并不包含在keySet结果中，使用时需要注意，详细可参考示例。
        Entity e = new Entity();
        e.setId(null);
        e.setName("hello");
        Map<String, Entity> map = Collections.singletonMap("e", e);
        Collection<String> collection = (Collection<String>) JSONPath.eval(map, "$.e.keySet()");
        System.out.println(collection);

        e.setId(1);
        collection = (Collection<String>) JSONPath.eval(map, "$.e.keySet()");
        System.out.println(collection);
        System.out.println(JSONPath.keySet(map, "$.e"));
        System.out.println(new JSONPath("$.e").keySet(map));


    }
}
