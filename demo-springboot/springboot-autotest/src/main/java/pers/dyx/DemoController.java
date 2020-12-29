package pers.dyx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dyx
 * @date 2020/9/14 14:21
 */
@Controller
public class DemoController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "Hello";
    }
}
