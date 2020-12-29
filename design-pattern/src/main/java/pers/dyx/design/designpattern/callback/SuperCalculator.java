package pers.dyx.design.designpattern.callback;

/**
 * @author: dyx
 * @date: 2018/10/31 13:36
 * @description:
 */
public class SuperCalculator {
    public void add(int a, int b, doJob customer) {
        int result = a + b;
        customer.fillBlank(a, b, result);
    }
}
