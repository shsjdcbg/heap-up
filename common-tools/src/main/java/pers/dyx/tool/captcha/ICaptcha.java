package pers.dyx.tool.captcha;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * 验证码接口
 *
 * @author dyx
 * @date 2020/9/716:58
 */
public interface ICaptcha extends Serializable {

    /**
     * 创建验证码
     */
    void createCode();

    /**
     * 获取验证码的文字内容
     *
     * @return 验证码文字内容
     */
    String getCode();

    /**
     * 验证码是否正确
     *
     * @param userInputValue 用户输入的验证码
     * @return 是否与生成的一致
     */
    boolean verify(String userInputValue);

    /**
     * 将验证码写出到流中
     *
     * @param outputStream 流
     */
    void write(OutputStream outputStream);
}
