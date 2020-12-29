package pers.dyx.util;

import java.security.SecureRandom;
import java.util.Date;

/**
 * @author dyx
 * @date 2019/1/28 09:52
 */
public class RandomUtil {

    public static void main(String[] args) {
        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < 20; i++) {
            System.out.println(rand.nextInt(50));
        }
    }
}
