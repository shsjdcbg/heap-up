package pers.dyx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.dyx.annotation.SystemControllerLog;

/**
 * @author dyx
 * @date 2020/9/14 14:21
 */
@Controller
public class DemoController {

    @RequestMapping("/test")
    @ResponseBody
    @SystemControllerLog(description = "测试")
    public void login() {
    }
}
