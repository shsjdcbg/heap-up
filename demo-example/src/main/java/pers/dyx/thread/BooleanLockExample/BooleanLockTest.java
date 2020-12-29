package pers.dyx.thread.BooleanLockExample;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author: dyx
 * @date: 2018/11/6 14:11
 * @description:
 */
public class BooleanLockTest {
    private Lock lock = new BooleanLock();

    public void syncMethod() {
        try {
            lock.lock();
            int randomInt = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread().getName() + "获得锁");
            TimeUnit.SECONDS.sleep(randomInt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String args[]) {
        BooleanLockTest booleanLockTest = new BooleanLockTest();
        IntStream.range(0, 10)
                .mapToObj(i -> new Thread(booleanLockTest::syncMethod))
                .forEach(Thread::start);
    }
}
