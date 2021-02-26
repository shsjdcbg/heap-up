package pers.dyx.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pers.dyx.feign.conf.FeignConfiguration;

/**
 * FeignClient 这个注解标识当前是一个 Feign 的客户端，value 属性是对应的服务名称，也就是你需要调用哪个服务中的接口。
 * value 指定eureka客户端的名称
 * configuration 指定使用的配置类
 *
 * @author dyx
 * @date 2021/2/25 17:31
 */
@FeignClient(value = "demo-eureka-client", configuration = FeignConfiguration.class)
public interface FeignDemoClient {

    @GetMapping("/")
    String hello();

}
