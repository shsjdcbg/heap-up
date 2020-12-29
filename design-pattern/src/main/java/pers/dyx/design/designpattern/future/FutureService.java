package pers.dyx.design.designpattern.future;

/**
 * @author: dyx
 * @date: 2018/11/8 13:40
 * @description: 用于提交任务
 */
public interface FutureService<IN,OUT> {
    Future<?> submit(Runnable runnable);

    Future<OUT> submit(Task<IN,OUT> task, IN input);

    static <T,R> FutureService<T,R> newService(){
        return new FutureServiceImpl<>();
    }
}
