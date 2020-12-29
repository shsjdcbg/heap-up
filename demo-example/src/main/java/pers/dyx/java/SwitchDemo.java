package pers.dyx.java;

/**
 * @author dyx
 * @version 1.0
 * @date 2020/7/20 13:40
 */
public class SwitchDemo {

    public static void main(String[] arg) {
        int i = 1, j = 0;
        switch (i) {
            case 2:
                j += 6;
            case 4:
                j += 1;
            default:
                j += 2;
            case 0:
                j += 4;
        }
        System.out.println(i + " " + j);

        i = 1;
        j = 0;
        switch (i) {
            case 2:
                j += 6;
                break;
            case 4:
                j += 1;
                break;
            default:
                j += 2;
                break;
            case 0:
                j += 4;
                break;
        }
        System.out.println(i + " " + j);


    }
}
