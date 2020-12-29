package pers.dyx.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: dyx
 * @date: 2018/11/6 09:49
 * @description: 通过检查线程interrupt的标识来决定是否退出
 */
public class InterruptThreadExit {
    public static void main(String args[]) {
        Thread t = new Thread() {
            @Override
            public void run() {
                System.out.println("I will start work");
                while (!isInterrupted()) {
                    //working
                }
                System.out.println("I will be exiting");
            }
        };

        t.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("System will be shutdown.");
        t.interrupt();
    }
}
