package pers.dyx.annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：
 *
 * @author dyx
 * @date 2020/4/29 11:49
 */
public class TestAnnotation {

    public static void main(String[] args) {
        System.out.println("测试开始......");
        Map<String, Object> map = getData();
        //遍历hashMap
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String item = entry.getKey();
            GirlVo girlVo = (GirlVo) entry.getValue();
            ReflectCallMethodAnnotation.annotationMethod(item, girlVo);
        }

        System.out.println("测试结束......");
    }


    public static Map<String, Object> getData() {

        HashMap<String, Object> data = new HashMap<>();

        GirlVo girlVo1 = new GirlVo();
        girlVo1.setGirlId("1001");
        girlVo1.setGirlName("张一");
        girlVo1.setAge(27);
        girlVo1.setHeight(169);
        girlVo1.setCupSize("F");

        GirlVo girlVo2 = new GirlVo();
        girlVo2.setGirlId("1002");
        girlVo2.setGirlName("王二");
        girlVo2.setAge(29);
        girlVo2.setHeight(175);
        girlVo2.setCupSize("B");

        GirlVo girlVo3 = new GirlVo();
        girlVo3.setGirlId("1003");
        girlVo3.setGirlName("刘五");
        girlVo3.setAge(24);
        girlVo3.setHeight(160);
        girlVo3.setCupSize("B");

        GirlVo girlVo4 = new GirlVo();
        girlVo4.setGirlId("1004");
        girlVo4.setGirlName("陈六");
        girlVo4.setAge(21);
        girlVo4.setHeight(168);
        girlVo4.setCupSize("C");

        GirlVo girlVo5 = new GirlVo();
        girlVo5.setGirlId("1005");
        girlVo5.setGirlName("赵七");
        girlVo5.setAge(25);
        girlVo5.setHeight(169);
        girlVo5.setCupSize("C");

        GirlVo girlVo6 = new GirlVo();
        girlVo6.setGirlId("1006");
        girlVo6.setGirlName("张八");
        girlVo6.setAge(26);
        girlVo6.setHeight(165);
        girlVo6.setCupSize("A");

        data.put("1001", girlVo1);
        data.put("1002", girlVo2);
        data.put("1003", girlVo3);
        data.put("1004", girlVo4);
        data.put("1005", girlVo5);
        data.put("1006", girlVo6);

        return data;
    }

}
