package pers.dyx.utils;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: dyx
 * @date: 2018/10/30 16:10
 * @description: 断线重连，重连间隔指数级递增
 */
public class RandomConnect {

    private final AtomicInteger connectPeriodTag = new AtomicInteger(0);

    public int connectTaskPeriod;

    public void randomConnectPeriod() {
        int start = (int) (Math.pow(2, connectPeriodTag.get()) * 10);
        int end = (int) (Math.pow(2, connectPeriodTag.get() + 1) * 10);
        connectTaskPeriod = getRandom(start, end);
        if (connectTaskPeriod >= 10 * 60 * 1000) {
            connectTaskPeriod = 10 * 60 * 1000;
        } else {
            connectPeriodTag.incrementAndGet();
        }
    }

    /**
     * 在某一区间生成随机数
     *
     * @param min 区间最小值
     * @param max 区间最大值
     * @return 区间随机数
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    public static void main(String[] args) {
        RandomConnect randomConnect = new RandomConnect();
        while (true) {
            randomConnect.randomConnectPeriod();
            System.out.println(randomConnect.connectTaskPeriod);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
