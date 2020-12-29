package pers.dyx.design.designpattern.callback;

/**
 * @author: dyx
 * @date: 2018/10/31 13:37
 * @description: 回调
 */
public class Test {
    public static void main(String[] args) {
        int a = 56;
        int b = 31;
        int c = 26497;
        int d = 11256;
        Student s1 = new Student("小明");
        Seller s2 = new Seller("老婆婆");
        s1.setDoJob(new doHomeWork());
        s2.setDoJob(new doHomeWork());
        s1.callHelp(a, b);
        s2.callHelp(c, d);
    }
}
