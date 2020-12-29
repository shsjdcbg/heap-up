package pers.dyx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.dyx.conf.UserAutoConf;
import pers.dyx.conf.UserConf;

import javax.annotation.Resource;

/**
 * @author dyx
 * @date 2020/9/14 14:21
 */
@Controller
public class DemoController {

    @Resource
    private UserConf userConf;

    @Resource
    private UserAutoConf userAutoConf;

    @Autowired // 注入到容器中
    private Environment environment;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        userAutoConf.getAboolean();
        environment.getProperty("12");
        return userConf.getStringTest() + userConf.getIntegerTest() + userConf.getBooleanTest() + userConf.getStringTestDefault();
    }
}
