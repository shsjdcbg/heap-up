package pers.dyx.feign.conf;

import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign 配置
 *
 * @author dyx
 * @date 2021/2/26 8:46
 */
@Configuration
public class FeignConfiguration {

    /**
     * 日志级别
     *
     * @return 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * Basic 认证配置
     *
     * @return 认证配置
     */
//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("user", "password");
//    }
}
