package pers.dyx.ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 添加@EnableEurekaClient注解作为Eureka客户端
 *
 * @author dyx
 * @date 2021/2/24 10:25
 */
@SpringBootApplication
@EnableEurekaClient
public class RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonApplication.class, args);
    }
}
