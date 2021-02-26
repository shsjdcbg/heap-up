package pers.dyx.hystrix.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import pers.dyx.hystrix.conf.FeignConfiguration;

/**
 * FeignClient 这个注解标识当前是一个 Feign 的客户端，value 属性是对应的服务名称，也就是你需要调用哪个服务中的接口。
 * value 指定eureka客户端的名称
 * configuration 指定使用的配置类
 * fallback 指定 fallback 进行回退
 * 通过 fallback 已经可以实现服务不可用时回退的功能，如果你想知道触发回退的原因，可以使用 FallbackFactory 来实现回退功能
 * (fallback 和 FallbackFactory 二者择其一，推荐后者)
 *
 * @author dyx
 * @date 2021/2/25 17:31
 */
@FeignClient(value = "demo-eureka-client", configuration = FeignConfiguration.class,
        fallbackFactory = FeignDemoFallbackFactory.class)
public interface FeignDemoClient {

    @GetMapping("/")
    String hello();

}
