package pers.dyx.annotation;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

/**
 * Description：
 *
 * @author dyx
 * @date 2020/4/29 11:48
 */
public class ReflectCallMethodAnnotation {
    public static void annotationMethod(String item,GirlVo girlVo){

        WorkService workService = new WorkService();

        Class clazz = WorkService.class;
        List<Method> methods = MethodUtils.getMethodsListWithAnnotation(clazz,Item.class);
        for (Method method : methods) {
            for (Annotation annotation : method.getDeclaredAnnotations()) {
                if (annotation instanceof Item){
                    String code = ((Item) annotation).value();
                    if (Objects.equals(item, code)){
                        try {
                            System.out.println("注解号:" + code +" 执行开始...");
                            System.out.println(method.invoke(workService, girlVo));
                            System.out.println("注解号:" + code +" 执行结束...");
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("抛异常...");
                        }
                    }
                }
            }
        }

    }
}
