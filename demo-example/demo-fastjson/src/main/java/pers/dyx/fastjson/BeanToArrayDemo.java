package pers.dyx.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import pers.dyx.fastjson.vo.Company;
import pers.dyx.fastjson.vo.Department;
import pers.dyx.fastjson.vo.Model;

/**
 * 在fastjson中，支持一种叫做BeanToArray的映射模式。普通模式下，JavaBean映射成json object，BeanToArray模式映射为json array。
 *
 * @author dyx
 * @date 2020/12/31 17:14
 */
public class BeanToArrayDemo {
    public static void main(String[] args) {
        Model model = new Model(1001, "gaotie");
        System.out.println(JSON.toJSONString(model));
        System.out.println(JSON.toJSONString(model, SerializerFeature.BeanToArray));


        // BeanToArray可以局部使用
        Company company = new Company();
        company.setCode(100);
        company.getDepartments().add(new Department(1001, "Sales"));
        company.getDepartments().add(new Department(1002, "Financial"));
        System.out.println(JSON.toJSONString(company));
    }
}
