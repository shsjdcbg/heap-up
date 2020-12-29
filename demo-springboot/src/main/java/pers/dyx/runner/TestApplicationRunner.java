package pers.dyx.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SpringBoot提供了两个接口来实现Spring容器启动完成后执行的功能，两个接口分别为CommandLineRunner和ApplicationRunner。
 * 这两个接口需要实现一个run方法，将代码在run中实现即可。这两个接口功能基本一致，其区别在于run方法的入参。
 * ApplicationRunner的run方法入参为ApplicationArguments，而CommandLineRunner的run方法入参为String数组。
 * 何为ApplicationArguments
 * 官方文档解释为：Provides access to the arguments that were used to run a SpringApplication.
 * 在Spring应用运行时使用的访问应用参数。即我们可以获取到SpringApplication.run(…)的应用参数。
 * <p>
 * Order注解
 * 当有多个类实现了CommandLineRunner和ApplicationRunner接口时，可以通过在类上添加@Order注解来设定运行顺序。
 *
 * @author dyx
 * @date 2020/11/3 11:29
 */
@Component
@Order(1)
public class TestApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("order1:TestApplicationRunner");
    }
}
