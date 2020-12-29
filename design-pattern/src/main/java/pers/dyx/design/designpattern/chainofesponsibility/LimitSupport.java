package pers.dyx.design.designpattern.chainofesponsibility;

/**
 * Description：
 *
 * @author dyx
 * @date 2019/3/7 16:17
 */
public class LimitSupport extends Support {

    private int limit;

    public LimitSupport(String name, int limit) {
        super(name);
        this.limit = limit;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return trouble.getNumber() < limit;
    }
}

