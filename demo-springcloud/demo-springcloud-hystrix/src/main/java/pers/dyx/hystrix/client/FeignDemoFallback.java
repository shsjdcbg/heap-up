package pers.dyx.hystrix.client;

import org.springframework.stereotype.Component;

/**
 * 继承FeignDemoClient，返回回退时的内容
 *
 * @author dyx
 * @date 2021/2/26 9:34
 */
@Component
public class FeignDemoFallback implements FeignDemoClient {
    @Override
    public String hello() {
        return "fail";
    }
}
