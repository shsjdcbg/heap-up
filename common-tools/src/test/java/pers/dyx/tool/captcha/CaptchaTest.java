package pers.dyx.tool.captcha;

import org.junit.Assert;
import org.junit.Test;
import pers.dyx.tool.captcha.CaptchaUtil;
import pers.dyx.tool.captcha.LineCaptcha;

/**
 * @author dyx
 * @date 2020/9/89:14
 */
public class CaptchaTest {

    @Test
    public void lineCaptchaTest1() {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        Assert.assertNotNull(lineCaptcha.getCode());
        Assert.assertTrue(lineCaptcha.verify(lineCaptcha.getCode()));
    }

}
