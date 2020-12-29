package pers.dyx.tool.localcache;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 软引用缓存
 *
 * @author dyx
 * @date 2020/9/715:49
 */
public class SoftCache implements ICache {
    private final ICache delegate;
    private final Deque<Object> hardLinksToAvoidGarbageCollection;
    private final ReferenceQueue<Object> queueOfGarbageCollectedEntries;
    private int numberOfHardLinks;

    public SoftCache(ICache delegate) {
        this.delegate = delegate;
        this.numberOfHardLinks = 256;
        this.hardLinksToAvoidGarbageCollection = new LinkedList<>();
        this.queueOfGarbageCollectedEntries = new ReferenceQueue<>();
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void put(Object key, Object value) {
        removeGarbageCollectedItems();
        //putObject存了一个SoftReference，这样value没用时会自动垃圾回收
        delegate.put(key, new SoftEntry(key, value, queueOfGarbageCollectedEntries));
    }

    @Override
    public Object get(Object key) {
        Object result = null;
        SoftReference<Object> softReference = (SoftReference<Object>) delegate.get(key);
        if (softReference != null) {
            result = softReference.get();
            if (result == null) {
                delegate.remove(key);
            } else {
                synchronized (hardLinksToAvoidGarbageCollection) {
                    hardLinksToAvoidGarbageCollection.addFirst(result);
                    if (hardLinksToAvoidGarbageCollection.size() > numberOfHardLinks) {
                        hardLinksToAvoidGarbageCollection.removeLast();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Object remove(Object key) {
        removeGarbageCollectedItems();
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        synchronized (hardLinksToAvoidGarbageCollection) {
            hardLinksToAvoidGarbageCollection.clear();
        }
        removeGarbageCollectedItems();
        delegate.clear();
    }

    @Override
    public int getSize() {
        removeGarbageCollectedItems();
        return delegate.getSize();
    }

    public void setSize(int size) {
        this.numberOfHardLinks = size;
    }

    private void removeGarbageCollectedItems() {
        SoftEntry sv;
        //查看被垃圾回收的引用队列，然后调用removeObject移除他们
        while ((sv = (SoftEntry) queueOfGarbageCollectedEntries.poll()) != null) {
            delegate.remove(sv.key);
        }
    }

    private static class SoftEntry extends SoftReference<Object> {
        private final Object key;

        SoftEntry(Object key, Object value, ReferenceQueue<Object> garbageCollectionQueue) {
            super(value, garbageCollectionQueue);
            this.key = key;
        }
    }
}
