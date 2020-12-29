package pers.dyx.java;

/**
 * @author dyx
 * @version 1.0
 * @date 2020/7/21 15:53
 */
public class NullDemo {

    public static void main(String[] arg) {

        // 可以将null赋予任何引用类型，也可以将null转化成任何类型
        String str = null;
        Integer itr = null;
        Double dbl = null;
        String myStr = (String) null;
        Integer myItr = (Integer) null;
        Double myDbl = (Double) null;

        // 不能将null赋给基本类型变量，例如int、double、float、boolean。如果你那样做了，编译器将会报错
//        int i = null;
//        short s = null;
//        byte b = null;
//        double d = null;

        // 如果将null赋值给包装类object，然后将object赋给各自的基本类型，编译器不会报，但是在运行时期会抛出空指针异常
//        Integer itr = null;
//        int j = itr;
//        System.out.println(j);

        System.out.println("如果使用了带有null值的引用类型变量，instanceof操作将会返回false");
        Integer iAmNull = null;
        if (iAmNull instanceof Integer) {
            System.out.println("iAmNull is instance of Integer");
        } else {
            System.out.println("iAmNull is NOT an instance of Integer");
        }

        // 不能用一个值为null的引用类型变量来调用非静态方法，会抛出空指针异常
//        NullDemo myObject = null;
//        myObject.iAmNonStaticMethod();
//        String b = myObject.a;
//        "".equals(null);

        System.out.println("你可以使用==或者!=操作来比较null值，但是不能使用其他算法或者逻辑操作，例如小于或者大于");
        String abc = null;
        String cde = null;
        if (abc == cde) {
            System.out.println("null == null is true");
        }

        if (null != null) {
            System.out.println("null != null is false");
        }

        if (abc == null) {
            System.out.println("true");
        }

    }

    private void iAmNonStaticMethod() {
        System.out.println("I am NON static method, don't date to call me by null");
    }

    public String a;

}
