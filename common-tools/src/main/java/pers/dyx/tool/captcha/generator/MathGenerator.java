package pers.dyx.tool.captcha.generator;

import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 数字计算验证码生成器
 *
 * @author dyx
 * @date 2020/9/717:29
 */
public class MathGenerator implements ICodeGenerator {

    private static final long serialVersionUID = -5514819971774091076L;

    private static final String operators = "+-*";

    /**
     * 参与计算数字最大长度
     */
    private final int numberLength;

    public MathGenerator(int numberLength) {
        this.numberLength = numberLength;
    }

    @Override
    public String generator() {
        final int limit = this.getLimit();
        String number1 = Integer.toString(ThreadLocalRandom.current().nextInt(limit));
        String number2 = Integer.toString(ThreadLocalRandom.current().nextInt(limit));
        number1 = StringUtils.rightPad(number1, this.numberLength, ' ');
        number2 = StringUtils.rightPad(number2, this.numberLength, ' ');
        char operator = operators.charAt(ThreadLocalRandom.current().nextInt(operators.length()));
        return number1 + operator + number2 + "=";
    }

    @Override
    public boolean verity(String code, String userInputCode) {
        int result;
        try {
            result = Integer.parseInt(userInputCode);
        } catch (NumberFormatException e) {
            return false;
        }

        final int a = Integer.parseInt(StringUtils.substring(code, 0, this.numberLength).trim());
        final char operator = code.charAt(this.numberLength);
        final int b = Integer.parseInt(StringUtils.substring(code, this.numberLength + 1, this.numberLength + 1 + this.numberLength).trim());

        switch (operator) {
            case '+':
                return (a + b) == result;
            case '-':
                return (a - b) == result;
            case '*':
                return (a * b) == result;
            default:
                return false;
        }
    }

    /**
     * 根据长度获取参与计算数字最大值
     *
     * @return 最大值
     */
    private int getLimit() {
        return Integer.parseInt("1" + StringUtils.repeat('0', this.numberLength));
    }
}
