package pers.dyx.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author: dyx
 * @date: 2018/11/8 09:34
 * @description:
 */
public class VolatileTest {
    private static volatile int i = 0;
    private static final CountDownLatch LATCH = new CountDownLatch(10);

    private static void inc() {
        i++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int x = 0; x < 1000; x++) {
                    inc();
                }
                LATCH.countDown();
            }).start();
        }
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i);
    }

}
