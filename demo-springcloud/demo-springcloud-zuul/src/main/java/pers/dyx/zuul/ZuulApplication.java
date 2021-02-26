package pers.dyx.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 添加@@EnableZuulProxy 开启 zuul功能
 * EnableZuulProxy 已经自带了 @EnableDiscoveryClient
 *
 * @author dyx
 * @date 2021/2/24 10:25
 */
@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
