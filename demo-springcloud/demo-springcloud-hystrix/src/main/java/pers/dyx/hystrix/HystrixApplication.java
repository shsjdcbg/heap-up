package pers.dyx.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 在启动类上添加 @EnableHystrix 或者 @EnableCircuitBreaker。
 * 注意，@EnableHystrix 中包含了 @EnableCircuitBreaker。
 * 添加 @EnableHystrixDashboard 开启熔断仪表盘查看监控数据
 *
 * @author dyx
 * @date 2021/2/24 10:25
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }
}
