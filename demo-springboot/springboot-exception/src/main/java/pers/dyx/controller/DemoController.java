package pers.dyx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dyx.utils.JsonResult;
import pers.dyx.utils.ResultCode;

/**
 * @author Minbo
 */
@RestController
public class DemoController {

    protected static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/hello")
    public String hello() {
        return "Hello greetings from springboot-exception";
    }

    @GetMapping("/exception")
    public String exception() {
        int a = 10 / 0;
        return "exception，" + a;
    }

    @GetMapping("/error-result")
    public JsonResult error() {
        return new JsonResult(ResultCode.SUCCESS_FAIL, "error错误");
    }
}
