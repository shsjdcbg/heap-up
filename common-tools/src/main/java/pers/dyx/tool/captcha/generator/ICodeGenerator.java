package pers.dyx.tool.captcha.generator;

import java.io.Serializable;

/**
 * 验证码文字生成器
 *
 * @author dyx
 * @date 2020/9/717:08
 */
public interface ICodeGenerator extends Serializable {

    /**
     * 生成验证码
     *
     * @return 验证码字符串
     */
    String generator();

    /**
     * 验证用户输入的字符串是否与生成的字符串匹配
     *
     * @param code          生成的验证码字符串
     * @param userInputCode 用户输入的验证码字符串
     * @return 是否匹配
     */
    boolean verity(String code, String userInputCode);
}
