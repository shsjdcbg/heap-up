package pers.dyx.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: dyx
 * @date: 2018/11/6 08:28
 * @description:
 */
public class ThreadInterrupt {
    public static void main(String args[]) {
        System.out.println("Main thread is interrupted?" + Thread.interrupted());

        Thread.currentThread().interrupt();

        System.out.println("Main thread is interrupted?" + Thread.currentThread().isInterrupted());

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (InterruptedException e) {
            System.out.println("I well be interrupted still");
        }

    }
}
