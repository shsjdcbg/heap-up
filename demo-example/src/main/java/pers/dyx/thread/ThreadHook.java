package pers.dyx.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: dyx
 * @date: 2018/11/6 14:57
 * @description:
 */
public class ThreadHook {
    public static void main(String args[]){
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
