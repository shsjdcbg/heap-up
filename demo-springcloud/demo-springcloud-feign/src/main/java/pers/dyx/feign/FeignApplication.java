package pers.dyx.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 添加@EnableEurekaClient注解作为Eureka客户端
 * 添加@EnableFeignClients注解，如果你的 Feign 接口定义跟你的启动类不在同一个包名下，还需要制定扫描的包名
 *
 * @author dyx
 * @date 2021/2/24 10:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignApplication.class, args);
    }
}
