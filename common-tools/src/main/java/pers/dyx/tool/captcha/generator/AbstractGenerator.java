package pers.dyx.tool.captcha.generator;

/**
 * 验证码生成器抽象类
 *
 * @author dyx
 * @date 2020/9/717:17
 */
public abstract class AbstractGenerator implements ICodeGenerator {

    private static final long serialVersionUID = 8685744597154953479L;

    public static final String BASE_CHAR_NUMBER = "abcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * 验证码生成字符集合
     */
    protected final String baseStr;

    /**
     * 验证码长度
     */
    protected final int length;

    /**
     * 构造，使用字母+数字做为基础
     *
     * @param count 生成验证码长度
     */
    public AbstractGenerator(int count) {
        this(BASE_CHAR_NUMBER, count);
    }

    /**
     * 构造
     *
     * @param baseStr 基础字符集合，用于随机获取字符串的字符集合
     * @param length  生成验证码长度
     */
    public AbstractGenerator(String baseStr, int length) {
        this.baseStr = baseStr;
        this.length = length;
    }

    /**
     * 获取验证码长度
     *
     * @return 验证码长度
     */
    public int getLength() {
        return length;
    }
}
