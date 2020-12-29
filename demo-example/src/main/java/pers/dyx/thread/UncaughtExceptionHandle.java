package pers.dyx.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: dyx
 * @date: 2018/11/6 14:48
 * @description:
 */
public class UncaughtExceptionHandle {
    public static void main(String args[]){
        Thread.setDefaultUncaughtExceptionHandler((t,e)->{
            System.out.println(t.getName());
            e.printStackTrace();
        });

        final Thread thread = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(1/0);
        },"test-thread");

        thread.start();

    }
}
