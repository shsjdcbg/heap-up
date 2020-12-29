package pers.dyx.design.designpattern.chainofesponsibility;

/**
 * Description：
 *
 * @author dyx
 * @date 2019/3/7 16:16
 */
public class NoSupport extends Support {

    public NoSupport(String name) {
        super(name);
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return false;
    }

}
