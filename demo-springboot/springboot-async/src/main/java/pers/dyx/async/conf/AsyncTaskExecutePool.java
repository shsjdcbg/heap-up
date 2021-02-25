package pers.dyx.async.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 重新定义线程池的配置
 * <p>
 * 线程池配置的拒绝策略：当我们的线程数量高于线程池的处理速度时，任务会被缓存到本地的队列中。
 * 队列也是有大小的，如果超过了这个大小，就需要有拒绝的策略，不然就会出现内存溢出。
 * 目前支持两种拒绝策略：
 * AbortPolicy：直接抛出 java.util.concurrent.RejectedExecutionException 异常。
 * CallerRunsPolicy：主线程直接执行该任务，执行完之后尝试添加下一个任务到线程池中，这样可以有效降低向线程池内添加任务的速度。
 * <p>
 * 建议大家用 CallerRunsPolicy 策略，因为当队列中的任务满了之后，如果直接抛异常，那么这个任务就会被丢弃。
 * 如果是 CallerRunsPolicy 策略，则会用主线程去执行，也就是同步执行，这样操作最起码任务不会被丢弃。
 *
 * @author dyx
 * @date 2021/2/25 15:20
 */
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(AsyncTaskExecutePool.class);

    @Autowired
    private TaskThreadPoolConfig config;

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(config.getCorePoolSize());
        executor.setMaxPoolSize(config.getMaxPoolSize());
        executor.setQueueCapacity(config.getQueueCapacity());
        executor.setKeepAliveSeconds(config.getKeepAliveSeconds());
        executor.setThreadNamePrefix(config.getThreadNamePrefix());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        // 异步任务中异常处理
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(Throwable arg0, Method arg1, Object... arg2) {
                logger.error("==========================" + arg0.getMessage() + "=======================", arg0);
                logger.error("exception method:" + arg1.getName());
            }
        };
    }
}
