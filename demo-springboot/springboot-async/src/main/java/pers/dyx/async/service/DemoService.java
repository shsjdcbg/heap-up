package pers.dyx.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author dyx
 * @date 2021/2/25 15:10
 */
@Service
public class DemoService {
    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    /**
     * 添加@Async注解，我们可以直接在 Controller 中调用这个业务方法，它就是异步执行的，会在默认的线程池中去执行。
     * 需要注意的是，一定要在外部的类中去调用这个方法，如果在本类调用则不起作用，比如 this.saveLog()。
     * 最后在启动类上开启异步任务的执行，添加 @EnableAsync 即可。
     */
    @Async
    public void saveLog() {
        log.info("####saveLog####   2");
        log.info(Thread.currentThread().getName());
        log.info("####saveLog####   3");
    }
}
