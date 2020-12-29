package pers.dyx.annotation;

/**
 * Description：
 *
 * @author dyx
 * @date 2020/4/29 11:47
 */
public class WorkService {

    @Item("1001")
    public int getItem1001Info(GirlVo girl) {

        System.out.println("getItem1001Info输出:");
        System.out.println(girl.toString());
        return 1;
    }

    @Item("1002")
    public int getItem1002Info(GirlVo girl) {

        System.out.println("getItem1002Info输出:");
        System.out.println(girl.toString());
        return 1;
    }

    @Item("1003")
    public int getItem1003Info(GirlVo girl) {
        System.out.println("getItem1003Info输出:");
        System.out.println(girl.toString());
        return 1;
    }

    @Item("1004")
    public int getItem1004Info(GirlVo girl) {
        System.out.println("getItem1004Info输出:");
        System.out.println(girl.toString());
        return 1;
    }

    @Item("1005")
    public int getItem1005Info(GirlVo girl) {
        System.out.println("getItem1005Info输出:");
        System.out.println(girl.toString());
        return 1;
    }

    @Item("1006")
    public int getItem1006Info(GirlVo girl) {
        System.out.println("getItem1006Info输出:");
        System.out.println(girl.toString());
        return 1;
    }
}
