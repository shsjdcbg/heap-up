package pers.dyx.design.designpattern.future;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: dyx
 * @date: 2018/11/8 13:47
 * @description:
 */
public class FutureServiceImpl<IN,OUT> implements FutureService<IN,OUT>  {
    private final static String FUTURE_THREAD_PREFIX = "FUTURE-";
    private final AtomicInteger nextCounter = new AtomicInteger(0);
    private String getNextName(){
        return FUTURE_THREAD_PREFIX + nextCounter.getAndIncrement();
    }
    @Override
    public Future<?> submit(Runnable runnable) {
        final FutureTask<Void> futureTask = new FutureTask<>();
        new Thread(()->{
            runnable.run();
            futureTask.finish(null);
        },getNextName()).start();
        return futureTask;
    }

    @Override
    public Future<OUT> submit(Task<IN, OUT> task, IN input) {
        final FutureTask<OUT> futureTask = new FutureTask<>();
        new Thread(()->{
            OUT result = task.get(input);
            futureTask.finish(result);
        },getNextName()).start();
        return futureTask;
    }
}
