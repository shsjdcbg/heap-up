package pers.dyx.design.designpattern.future;

/**
 * @author: dyx
 * @date: 2018/11/8 13:44
 * @description:
 */
public class FutureTask<T> implements Future<T> {
    private T result;
    private boolean isDone = false;
    private final Object LOCK = new Object();

    @Override
    public T get() throws InterruptedException {
        synchronized (LOCK){
            while (!isDone){
                LOCK.wait();
            }
        }
        return result;
    }

    @Override
    public boolean done() {
        return isDone;
    }

    protected void finish(T result){
        synchronized (LOCK){
            if(isDone){
                return;
            }
            this.result = result;
            this.isDone = true;
            LOCK.notifyAll();
        }
    }
}
