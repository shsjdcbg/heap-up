package pers.dyx.tool.localcache;

import java.util.Deque;
import java.util.LinkedList;

/**
 * FIFO缓存
 *
 * @author dyx
 * @date 2020/9/715:24
 */
public class FifoCache implements ICache {

    private final ICache delegate;
    private Deque<Object> keyList;
    private int size;

    public FifoCache(ICache delegate) {
        this.delegate = delegate;
        this.keyList = new LinkedList<>();
        this.size = 1024;
    }

    public FifoCache(ICache delegate, int size) {
        this.delegate = delegate;
        this.keyList = new LinkedList<>();
        this.size = size;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void put(Object key, Object value) {
        cycleKeyList(key);
        delegate.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return delegate.get(key);
    }

    @Override
    public Object remove(Object key) {
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        delegate.clear();
        keyList.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    public void setSize(int size) {
        this.size = size;
    }

    private void cycleKeyList(Object key) {
        //增加记录时判断如果记录已超过size，会移除链表的第一个元素，从而达到FIFO缓存效果
        keyList.addLast(key);
        if (keyList.size() > size) {
            Object oldestKey = keyList.removeFirst();
            delegate.remove(oldestKey);
        }
    }
}
