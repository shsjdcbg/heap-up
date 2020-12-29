package pers.dyx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.dyx.limit.annotation.Limit;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * demo
 *
 * @author dyx
 * @date 2020/9/89:23
 */
@Controller
public class DemoController {

    /**
     * 10秒内可以请求1次
     */
    @RequestMapping("/test")
    @ResponseBody
    @Limit(key = "test", limit = 1, second = 10)
    public String test() {
        return "可以请求了";
    }

}
