package pers.dyx.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
     * 增加一个 @HystrixCommand 注解，用于指定依赖服务调用延迟或失败时调用的方法
     *
     * @return
     */
    @RequestMapping("/")
    @HystrixCommand(fallbackMethod = "defaultCallHello")
    public String home() {
        return restTemplate.getForObject("http://demo-eureka-client", String.class);
    }

    /**
     * 当调用失败触发熔断时会用 defaultCallHello 方法来回退具体的内容
     *
     * @return
     */
    public String defaultCallHello() {
        return "fail";
    }

}
