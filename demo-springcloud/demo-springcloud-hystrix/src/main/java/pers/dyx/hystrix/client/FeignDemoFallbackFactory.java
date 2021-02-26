package pers.dyx.hystrix.client;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过 fallback 已经可以实现服务不可用时回退的功能，如果你想知道触发回退的原因，可以使用 FallbackFactory 来实现回退功能
 *
 * @author dyx
 * @date 2021/2/26 9:41
 */
@Component
public class FeignDemoFallbackFactory implements FallbackFactory<FeignDemoClient> {
    private final static Logger logger = LoggerFactory.getLogger(FeignDemoFallbackFactory.class);

    @Override
    public FeignDemoClient create(final Throwable cause) {
        logger.error("FeignDemoClient回退：", cause);
        return new FeignDemoClient() {
            @Override
            public String hello() {
                return "fail";
            }
        };
    }
}
