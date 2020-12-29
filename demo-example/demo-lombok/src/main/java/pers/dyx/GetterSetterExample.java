package pers.dyx;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * 有了@Getter/@Setter注解，我们再也不用编写getter/setter方法了。
 * 试想下之前即使我们使用IDEA自动生成getter/setter方法，如果类属性的类型和名称改了，又要重新生成getter/setter方法也是一件很麻烦的事情。
 *
 * @author dyx
 * @date 2020/12/29 15:56
 */
public class GetterSetterExample {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private Integer age;

    public static void main(String[] args) {
        GetterSetterExample example = new GetterSetterExample();
        example.setName("test");
        example.setAge(20);
        System.out.printf("name:%s age:%d", example.getName(), example.getAge());
    }
}
