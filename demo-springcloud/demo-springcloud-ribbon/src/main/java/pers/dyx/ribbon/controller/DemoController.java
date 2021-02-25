package pers.dyx.ribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author dyx
 * @date 2021/2/24 10:44
 */
@RestController
public class DemoController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 接口调用的代码，将 IP+PORT 改成服务名称，也就是注册到 Eureka 中的名称
     * 接口调用的时候，框架内部会将服务名称替换成具体的服务 IP 信息，然后进行调用。
     *
     * @return
     */
    @RequestMapping("/")
    public String home() {
        return restTemplate.getForObject("http://demo-eureka-client", String.class);
    }

}
