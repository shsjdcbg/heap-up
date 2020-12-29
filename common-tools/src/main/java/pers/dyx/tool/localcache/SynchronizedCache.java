package pers.dyx.tool.localcache;

/**
 * 同步缓存
 *
 * @author dyx
 * @date 2020/9/716:01
 */
public class SynchronizedCache implements ICache {

    private final ICache delegate;

    public SynchronizedCache(ICache delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public synchronized void put(Object key, Object value) {
        delegate.put(key, value);
    }

    @Override
    public synchronized Object get(Object key) {
        return delegate.get(key);
    }

    @Override
    public synchronized Object remove(Object key) {
        return delegate.remove(key);
    }

    @Override
    public synchronized void clear() {
        delegate.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }
}
