package pers.dyx.tool.localcache;

import pers.dyx.utils.StringUtils;

import java.util.concurrent.*;

/**
 * Description：带有过期时间的本地缓存
 * localcache有着极大的性能优势：
 * 1. 单机情况下适当使用localcache会使应用的性能得到很大的提升。
 * 2. 集群环境下对于敏感性要求不高的数据可以使用localcache，只配置简单的失效机制来保证数据的相对一致性。
 * 哪些数据可以存储到本地缓存？
 * 1．访问频繁的数据；
 * 2．静态基础数据（长时间内不变的数据）；
 * 3．相对静态数据（短时间内不变的数据）。
 *
 * @author dyx
 * @date 2019/3/28 10:18
 */
public class ExpireCache {

    /**
     * 定时线程
     */
    private static final ScheduledExecutorService SWAP_EXPIRED_POOL = new ScheduledThreadPoolExecutor(10);

    private final ConcurrentHashMap<String, Node> cache = new ConcurrentHashMap<>(1024);

    /**
     * 让过期时间最小的数据排在队列前，在清除过期数据时，只需查看缓存最近的过期数据，而不用扫描全部缓存
     */
    private final BlockingQueue<Node> expireQueue = new PriorityBlockingQueue<>(1024);

    /**
     * 使用默认的线程池，每5秒清除一次过期数据,线程池和调用频率 最好是交给调用者去设置。
     *
     * @param clearRate 清理频率，单位：秒
     */
    public ExpireCache(int clearRate) {
        SWAP_EXPIRED_POOL.scheduleWithFixedDelay(new SwapExpiredNodeWork(), clearRate, clearRate, TimeUnit.SECONDS);
    }

    public Object set(String key, Object value, long ttl) {
        if (StringUtils.isEmpty(key)) {
            throw new NullPointerException("key can't be empty");
        }
        if (ttl <= 0) {
            throw new NullPointerException("ttl must greater than 0");
        }

        long expireTime = System.currentTimeMillis() + ttl;
        Node newNode = new Node(key, value, expireTime);
        Node old = cache.put(key, newNode);
        expireQueue.add(newNode);
        //如果该key存在数据，从过期时间队列删除
        if (old != null) {
            expireQueue.remove(old);
            return old.value;
        }
        return null;
    }

    /**
     * 拿到的数据可能是已经过期的数据，可以再次判断一下
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        Node n = cache.get(key);
        return n == null ? null : n.value;
    }

    /**
     * 删出KEY，并返回该key对应的数据
     *
     * @param key 键
     * @return 值
     */
    public Object remove(String key) {
        Node n = cache.remove(key);
        if (n == null) {
            return null;
        } else {
            expireQueue.remove(n);
            return n.value;
        }
    }

    /**
     * 删除已经过期的数据
     */
    class SwapExpiredNodeWork implements Runnable {
        @Override
        public void run() {
            long now = System.currentTimeMillis();
            while (true) {
                Node node = expireQueue.peek();
                //没有数据了，或者数据都是没有过期的了
                if (node == null || node.expireTime > now) {
                    return;
                }
                cache.remove(node.key);
                expireQueue.poll();
            }
        }
    }

    static class Node implements Comparable<Node> {
        private final String key;
        private final Object value;
        private final long expireTime;

        Node(String key, Object value, long expireTime) {
            this.value = value;
            this.key = key;
            this.expireTime = expireTime;
        }

        @Override
        public int compareTo(Node o) {
            long r = this.expireTime - o.expireTime;
            if (r > 0) {
                return 1;
            }
            if (r < 0) {
                return -1;
            }
            return 0;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String value = "12";
        ExpireCache expireCache = new ExpireCache(5);
        expireCache.set("12", null, 10000L);

        System.out.println(expireCache.get("12"));
        while (true) {
            TimeUnit.SECONDS.sleep(1);
            value = (String) expireCache.get("12");
            System.out.println(value);
        }
    }
}
