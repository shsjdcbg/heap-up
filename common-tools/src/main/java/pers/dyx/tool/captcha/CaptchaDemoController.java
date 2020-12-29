package pers.dyx.tool.captcha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author dyx
 * @date 2020/9/89:23
 */
@Controller
public class CaptchaDemoController {

    @RequestMapping("/captcha")
    public void captcha(HttpServletResponse response) throws IOException {
        // 定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        String code = lineCaptcha.getCode();
        OutputStream out = response.getOutputStream();
        out.write(lineCaptcha.getImageBytes());

    }

}
