package pers.dyx.tool.localcache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 阻塞缓存装饰器
 * 当在缓存中找不到元素时，它将对缓存键设置锁定。这样，其他线程将等待直到该元素被填充，而不是访问数据库。
 *
 * @author dyx
 * @date 2020/9/413:51
 */
public class BlockingCache implements ICache {

    private long timeout;
    private final ICache delegate;
    private final ConcurrentHashMap<Object, ReentrantLock> locks;

    public BlockingCache(ICache delegate) {
        this.delegate = delegate;
        this.locks = new ConcurrentHashMap<>();
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void put(Object key, Object value) {
        try {
            delegate.put(key, value);
        } finally {
            releaseLock(key);
        }
    }

    @Override
    public Object get(Object key) {
        acquireLock(key);
        Object value = delegate.get(key);
        if (value != null) {
            releaseLock(key);
        }
        return value;
    }

    @Override
    public Object remove(Object key) {
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    private ReentrantLock getLockForKey(Object key) {
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock previous = locks.putIfAbsent(key, lock);
        return previous == null ? lock : previous;
    }

    /**
     * 加锁
     *
     * @param key 键
     */
    private void acquireLock(Object key) {
        Lock lock = getLockForKey(key);
        if (timeout > 0) {
            try {
                boolean acquired = lock.tryLock(timeout, TimeUnit.MILLISECONDS);
                if (!acquired) {
                    throw new CacheException("Couldn't get a lock in " + timeout + " for the key " + key + " at the cache " + delegate.getId());
                }
            } catch (InterruptedException e) {
                throw new CacheException("Got interrupted while trying to acquire lock for key " + key, e);
            }
        } else {
            lock.lock();
        }
    }

    /**
     * 释放锁
     *
     * @param key 键
     */
    private void releaseLock(Object key) {
        ReentrantLock lock = locks.get(key);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

}
