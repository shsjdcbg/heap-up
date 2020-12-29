package pers.dyx.tool.localcache;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 事务缓存
 *
 * @author dyx
 * @date 2020/9/716:19
 */
public class TransactionalCache implements ICache {

    private final ICache delegate;
    private boolean clearOnCommit;
    private final Map<Object, Object> entriesToAddOnCommit;
    private final Set<Object> entriesMissedInCache;

    public TransactionalCache(ICache delegate) {
        this.delegate = delegate;
        //默认commit时不清缓存
        this.clearOnCommit = false;
        this.entriesToAddOnCommit = new HashMap<Object, Object>();
        this.entriesMissedInCache = new HashSet<Object>();
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void put(Object key, Object value) {
        entriesToAddOnCommit.put(key, value);
    }

    @Override
    public Object get(Object key) {
        Object object = delegate.get(key);
        if (object == null) {
            entriesMissedInCache.add(key);
        }
        if (clearOnCommit) {
            return null;
        } else {
            return object;
        }
    }

    @Override
    public Object remove(Object key) {
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        clearOnCommit = true;
        entriesMissedInCache.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    public void commit() {
        if (clearOnCommit) {
            delegate.clear();
        }
        flushPendingEntries();
        reset();
    }

    public void rollback() {
        unlockMissedEntries();
        reset();
    }

    private void reset() {
        clearOnCommit = false;
        entriesToAddOnCommit.clear();
        entriesMissedInCache.clear();
    }

    private void flushPendingEntries() {
        for (Map.Entry<Object, Object> entry : entriesToAddOnCommit.entrySet()) {
            delegate.put(entry.getKey(), entry.getValue());
        }
        for (Object entry : entriesMissedInCache) {
            if (!entriesToAddOnCommit.containsKey(entry)) {
                delegate.put(entry, null);
            }
        }
    }

    private void unlockMissedEntries() {
        for (Object entry : entriesMissedInCache) {
            delegate.put(entry, null);
        }
    }

}
