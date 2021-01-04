package pers.dyx.gson.vo;

/**
 * @author dyx
 * @date 2021/1/4 9:51
 */
public class BagOfPrimitives {
    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;

    public BagOfPrimitives() {
        // 无参的构造方法
    }
}
