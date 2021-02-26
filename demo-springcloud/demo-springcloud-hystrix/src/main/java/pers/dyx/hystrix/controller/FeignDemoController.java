package pers.dyx.hystrix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.dyx.hystrix.client.FeignDemoClient;

/**
 * Feign整合Hystrix实现容错处理
 *
 * @author dyx
 * @date 2021/2/24 10:44
 */
@RestController
public class FeignDemoController {

    @Autowired
    private FeignDemoClient feignDemoClient;

    @RequestMapping("/feign")
    public String home() {
        return feignDemoClient.hello();
    }

}
