package pers.dyx;

import lombok.extern.java.Log;

/**
 * 使用@Log注解，可以直接生成日志对象log，通过log对象可以直接打印日志。
 * 使用Lombok生成日志对象时，根据使用日志实现的不同，有多种注解可以使用。比如@Log、@Log4j、@Log4j2、@Slf4j等。
 *
 * @author dyx
 * @date 2020/12/29 16:11
 */
@Log
public class LogExample {
    public static void main(String[] args) {
        log.info("level info");
        log.warning("level warning");
        log.severe("level severe");
    }
}
