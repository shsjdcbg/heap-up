package pers.dyx.design.designpattern.chainofesponsibility;

/**
 * Description：
 *
 * @author dyx
 * @date 2019/3/7 16:18
 */
public class Trouble {
    private int number;

    public Trouble(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Trouble{" +
                "number=" + number +
                '}';
    }
}
