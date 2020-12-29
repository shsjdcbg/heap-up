package pers.dyx.tool.localcache;

import java.util.HashMap;
import java.util.Map;

/**
 * 永久缓存
 *
 * @author dyx
 * @date 2020/9/414:25
 */
public class PerpetualCache implements ICache {

    /**
     * 每个永久缓存有一个ID来识别
     */
    private String id;

    private Map<Object, Object> cache = new HashMap<>();

    public PerpetualCache(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void put(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object get(Object key) {
        return cache.get(key);
    }

    @Override
    public Object remove(Object key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    @Override
    public boolean equals(Object o) {
        //只要id相等就认为两个cache相同
        if (getId() == null) {
            throw new CacheException("Cache instances require an ID.");
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof ICache)) {
            return false;
        }

        ICache otherCache = (ICache) o;
        return getId().equals(otherCache.getId());
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            throw new CacheException("Cache instances require an ID.");
        }
        return getId().hashCode();
    }
}
