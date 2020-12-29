package pers.dyx.design.designpattern.callback;

/**
 * @author: dyx
 * @date: 2018/10/31 13:51
 * @description:
 */
public class doHomeWork implements doJob {
    @Override
    public void fillBlank(int a, int b, int result) {
        System.out.println(a + " + " + b + " = " + result + "元");
    }

}
