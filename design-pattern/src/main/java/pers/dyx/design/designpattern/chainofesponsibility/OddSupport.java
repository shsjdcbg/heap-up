package pers.dyx.design.designpattern.chainofesponsibility;

/**
 * Description：
 *
 * @author dyx
 * @date 2019/3/7 16:17
 */
public class OddSupport extends Support {

    public OddSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return trouble.getNumber() % 2 == 1;
    }
}

