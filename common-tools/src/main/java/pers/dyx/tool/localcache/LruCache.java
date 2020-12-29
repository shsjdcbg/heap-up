package pers.dyx.tool.localcache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最少使用缓存
 *
 * @author dyx
 * @date 2020/9/715:30
 */
public class LruCache implements ICache {

    private final ICache delegate;
    private Map<Object, Object> keyMap;
    private Object eldestKey;

    public LruCache(ICache delegate) {
        this.delegate = delegate;
        setSize(1024);
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void put(Object key, Object value) {
        delegate.put(key, value);
        cycleKeyList(key);
    }

    @Override
    public Object get(Object key) {
        //get的时候调用一下LinkedHashMap.get，让经常访问的值移动到链表末尾
        keyMap.get(key);
        return delegate.get(key);
    }

    @Override
    public Object remove(Object key) {
        return delegate.remove(key);
    }

    @Override
    public void clear() {
        delegate.clear();
        keyMap.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    private void cycleKeyList(Object key) {
        keyMap.put(key, key);
        //keyMap是linkedhashmap，最老的记录已经被移除了，然后这里我们还需要移除被委托的那个cache的记录
        if (eldestKey != null) {
            delegate.remove(eldestKey);
            eldestKey = null;
        }
    }

    public void setSize(final int size) {
        keyMap = new LinkedHashMap<Object, Object>(size, .75F, true) {
            private static final long serialVersionUID = 4267176411845948333L;

            /**
             *覆盖removeEldestEntry方法，返回true或false告诉LinkedHashMap要不要删除最老键值
             * LinkedHashMap内部其实就是每次访问或者插入一个元素都会把元素放到链表末尾，这样不经常访问的键值肯定就在链表开头啦
             */
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                boolean tooBig = size() > size;
                if (tooBig) {
                    eldestKey = eldest.getKey();
                }
                return tooBig;
            }
        };

    }
}
