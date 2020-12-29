package pers.dyx.security.xss.blacklist;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * Description：Filter配置
 *
 * @author dyx
 * @date 2019/11/1 13:14
 */
@Configuration
public class XssFilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(xssFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("xssFilter");
        return registration;
    }

    @Bean(name = "xssFilter")
    public Filter xssFilter() {
        return new XssFilter();
    }

}

