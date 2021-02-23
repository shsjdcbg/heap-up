package pers.dyx.service;

/**
 * @author dyx
 * @date 2021/2/23 9:30
 */
public class DemoService {
    public String sayWhat;
    public String toWho;

    public DemoService(String sayWhat, String toWho) {
        this.sayWhat = sayWhat;
        this.toWho = toWho;
    }

    public String say() {
        return this.sayWhat + "!  " + toWho;
    }
}
