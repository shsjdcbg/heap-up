package pers.dyx.tool.captcha.generator;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 随机字符验证码生成器
 *
 * @author dyx
 * @date 2020/9/717:15
 */
public class RandomGenerator extends AbstractGenerator {
    private static final long serialVersionUID = -7802758587765561876L;

    public RandomGenerator(int count) {
        super(count);
    }

    public RandomGenerator(String baseStr, int length) {
        super(baseStr, length);
    }

    @Override
    public String generator() {
        return randomString(this.baseStr, this.length);
    }

    @Override
    public boolean verity(String code, String userInputCode) {
        if (StringUtils.isNotEmpty(userInputCode)) {
            return StringUtils.equalsIgnoreCase(code, userInputCode);
        }
        return false;
    }

    /**
     * 获得一个随机的字符串
     *
     * @param baseString 随机字符选取的样本
     * @param length     字符串的长度
     * @return 随机字符串
     */
    private static String randomString(String baseString, int length) {
        if (StringUtils.isEmpty(baseString)) {
            return StringUtils.EMPTY;
        }
        final StringBuilder sb = new StringBuilder(length);

        if (length < 1) {
            length = 1;
        }
        int baseLength = baseString.length();
        for (int i = 0; i < length; i++) {
            int number = ThreadLocalRandom.current().nextInt(baseLength);
            sb.append(baseString.charAt(number));
        }
        return sb.toString();
    }
}
