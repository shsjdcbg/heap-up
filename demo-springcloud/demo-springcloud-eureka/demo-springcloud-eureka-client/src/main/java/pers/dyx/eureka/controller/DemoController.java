package pers.dyx.eureka.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dyx
 * @date 2021/2/24 10:44
 */
@RestController
public class DemoController {

    @Autowired
    private EurekaClient discoveryClient;

    @RequestMapping("/")
    public String home() {
        return "hello world";
    }

    public String serviceUrl() {
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("demo-eureka-client", false);
        return instance.getHomePageUrl();
    }

}
