package pers.dyx.hystrix.conf;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 增加能够让 RestTemplate 具备负载均衡能力的注解 @LoadBalanced
 *
 * @author dyx
 * @date 2021/2/25 17:01
 */
@Configuration
public class BeanConfiguration {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
