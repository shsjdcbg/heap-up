package pers.dyx.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.dyx.properties.DemoProperties;
import pers.dyx.service.DemoService;

/**
 * 这里将DemoService类定义为一个Bean，交给Ioc容器。
 * ConditionalOnProperty 注解控制 @Configuration 是否生效。简单来说也就是我们可以通过在yml配置文件中控制 @Configuration 注解的配置类是否生效。
 *
 * @author dyx
 * @date 2021/2/23 9:31
 */
@Configuration
@ConditionalOnProperty(
        prefix = "demo",
        name = "isopen",
        havingValue = "true"
)
public class DemoConfig {
    @Autowired
    private DemoProperties demoProperties;

    @Bean(name = "demo")
    public DemoService demoService() {
        return new DemoService(demoProperties.getSayWhat(), demoProperties.getToWho());
    }
}
