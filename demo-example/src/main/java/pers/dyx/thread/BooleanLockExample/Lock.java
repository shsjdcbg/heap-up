package pers.dyx.thread.BooleanLockExample;

import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @author: dyx
 * @date: 2018/11/6 13:52
 * @description: Lock接口
 */
public interface Lock {
    /**
     * 永远阻塞，除非获取到了锁
     * @throws InterruptedException 中断
     */
    void lock() throws InterruptedException;

    /**
     * 可以中断，增加超时
     * @param mills
     * @throws InterruptedException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    /**
     * 锁的释放
     */
    void unlock();

    /**
     * 获取当前有哪些线程被阻塞
     * @return
     */
    List<Thread> getBlockedThreads();
}
