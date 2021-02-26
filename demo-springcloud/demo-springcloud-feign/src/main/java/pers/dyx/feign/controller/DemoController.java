package pers.dyx.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dyx.feign.FeignDemoClient;

/**
 * @author dyx
 * @date 2021/2/24 10:44
 */
@RestController
public class DemoController {

    @Autowired
    private FeignDemoClient feignDemoClient;

    @RequestMapping("/")
    public String home() {
        return feignDemoClient.hello();
    }

}
