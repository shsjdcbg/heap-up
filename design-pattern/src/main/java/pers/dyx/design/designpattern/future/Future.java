package pers.dyx.design.designpattern.future;

/**
 * @author: dyx
 * @date: 2018/11/8 13:38
 * @description: 提供获取计算结果和判断任务是否完成的两个接口
 */
public interface Future<T> {
    T get() throws InterruptedException;

    boolean done();
}
