package pers.dyx.thread;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author: dyx
 * @date: 2018/11/6 08:39
 * @description:
 */
public class TreadJoin {
    public static void main(String args[]) throws InterruptedException {
        List<Thread> threads = IntStream.range(1, 3)
                .mapToObj(TreadJoin::create).collect(toList());
        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
            shortSleep();
        }
    }

    private static Thread create(int seq) {
        return new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "#" + i);
                shortSleep();
            }
        }, String.valueOf(seq));
    }

    private static void shortSleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
