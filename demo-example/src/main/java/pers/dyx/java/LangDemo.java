package pers.dyx.java;

/**
 * @author dyx
 * @date 2020/6/15 13:19
 */
public class LangDemo {

    public static void main(String[] args) {

        // 基础数据类型与基础类型封装类初始值不同
        Lang lang = new Lang();
        System.out.println("基础数据类型与基础类型封装类初始值不同" + lang);


        // 基础数据类型仅表示数据类型，而封装类还包括这种数据类型的行为
        String s1 = "Hello World";
        String s2 = s1.substring(0, 5);


        // Java虚拟机处理方式不同：基础数据类型，会为其分配数据类型实际占用的内存空间，值直接保存在变量中；
        // 而封装类，仅仅是一个指向堆区中某个实例的指针，变量中保存的只是实际对象的地址
        int i = 0;
        Integer integer = 0;

        //值传递
        int age = 24;
        valueCross(age);
        System.out.println("方法执行后的值：" + age);

        Person person = new Person();
        person.setName("张三");
        person.setAge(24);
        personCross(person);
        System.out.println("方法执行后的person：" + person);


        // String VS StringBuilder VS StringBuffer
        String str = "hello";
        System.out.println("调用方法前的String值：" + str);
        foo(str);
        System.out.println("调用方法后的String值：" + str);
        StringBuilder stringBuilder = new StringBuilder("hello");
        System.out.println("调用方法前的StringBuilder值：" + stringBuilder);
        foo(stringBuilder);
        System.out.println("调用方法后的StringBuilder值：" + stringBuilder);
        StringBuilder stringBuilder1 = new StringBuilder("hello");
        System.out.println("调用方法前的StringBuilder1值：" + stringBuilder1);
        foo1(stringBuilder1);
        System.out.println("调用方法后的StringBuilder1值：" + stringBuilder1);
        StringBuffer stringBuffer = new StringBuffer("hello");
        System.out.println("调用方法前的StringBuffer值：" + stringBuffer);
        foo(stringBuffer);
        System.out.println("调用方法后的StringBuffer值：" + stringBuffer);

    }

    private static void valueCross(int age) {
        System.out.println("传入前的值：" + age);
        age = 25;
        System.out.println("修改之后的值：" + age);
    }

    private static void personCross(Person person) {
        System.out.println("传入前的person：" + person);
        person.setName("李四");
        person.setAge(25);
        System.out.println("修改后的person：" + person);
    }

    private static void foo(String text) {
        text = " world";
    }

    private static void foo(StringBuilder builder) {
        builder.append(" world");
    }

    private static void foo(StringBuffer builder) {
        builder.append(" world");
    }

    private static void foo1(StringBuilder builder) {
        builder = new StringBuilder("world");
    }

}
