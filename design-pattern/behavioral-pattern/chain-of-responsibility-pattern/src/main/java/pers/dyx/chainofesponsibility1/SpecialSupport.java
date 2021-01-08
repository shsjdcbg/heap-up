package pers.dyx.chainofesponsibility1;

/**
 * Description：
 *
 * @author dyx
 * @date 2019/3/7 16:17
 */
public class SpecialSupport extends Support {

    private int number;

    public SpecialSupport(String name, int number) {
        super(name);
        this.number = number;
    }

    @Override
    protected boolean resolve(Trouble trouble) {
        return trouble.getNumber() == number;
    }

}
