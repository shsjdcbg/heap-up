package pers.dyx.tool.mqtt;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: dyx
 * @date: 2018/10/30 15:54
 * @description: 断线重连
 */
public class ReConnectUtil {

    public static Boolean getInetStatus() {
        URL url = null;
        try {
            url = new URL("http://www.baicu.com/");
            url.openStream();
            return true;
            //关闭此输入流并释放与该流关联的所有系统资源。
        } catch (IOException e) {
            return false;
        }
    }

    private AtomicInteger connectPeriodTag = new AtomicInteger(0);

    private int connectTaskPeriod;

    public int randomConnectPeriod() {
        int start = (int) (Math.pow(2, connectPeriodTag.get()) * 10);
        int end = (int) (Math.pow(2, connectPeriodTag.get() + 1) * 10);
        connectTaskPeriod = getRandom(start, end);
        if (connectTaskPeriod >= 10 * 60 * 1000) {
            connectTaskPeriod = 10 * 60 * 1000;
        } else {
            connectPeriodTag.incrementAndGet();
        }
        return connectTaskPeriod;
    }

    /**
     * 在某一区间生成随机数
     *
     * @param min
     * @param max
     * @return
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    public static Long intToLong(int num) {
        return Long.parseLong(String.valueOf(num));
    }
}
