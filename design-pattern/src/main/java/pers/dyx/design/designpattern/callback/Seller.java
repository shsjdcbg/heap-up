package pers.dyx.design.designpattern.callback;

/**
 * @author: dyx
 * @date: 2018/10/31 13:44
 * @description:
 */
public class Seller {
    private String name = null;
    private doJob doJob = null;

    public Seller(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDoJob(doJob doJob) {
        this.doJob = doJob;
    }

    public void callHelp(int a, int b) {
        new SuperCalculator().add(a, b, doJob);
    }
}
