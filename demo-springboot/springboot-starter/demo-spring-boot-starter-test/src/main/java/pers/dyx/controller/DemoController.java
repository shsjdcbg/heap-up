package pers.dyx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dyx.service.DemoService;

import javax.annotation.Resource;

/**
 * @author dyx
 * @date 2021/2/23 10:00
 */
@RestController
public class DemoController {
    @Resource(name = "demo")
    private DemoService demoService;

    @GetMapping("/say")
    public String sayWhat() {
        return demoService.say();
    }

}
