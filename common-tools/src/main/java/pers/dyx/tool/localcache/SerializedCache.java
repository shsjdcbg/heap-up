package pers.dyx.tool.localcache;

import java.io.*;

/**
 * 序列化缓存
 *
 * @author dyx
 * @date 2020/9/715:40
 */
public class SerializedCache implements ICache {
    private final ICache delegate;

    public SerializedCache(ICache delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public void put(Object key, Object value) {
        if (value == null || value instanceof Serializable) {
            // 先序列化在存储
            delegate.put(key, serialize((Serializable) value));
        } else {
            throw new CacheException("SharedCache failed to make a copy of a non-serializable object: " + value);
        }
    }

    @Override
    public Object get(Object key) {
        // 先取值
        Object value = delegate.get(key);
        // 再反序列化
        return value == null ? null : deserialize((byte[]) value);
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

    private byte[] serialize(Serializable value) {
        try {
            //序列化核心就是ByteArrayOutputStream
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(value);
            oos.flush();
            oos.close();
            return bos.toByteArray();
        } catch (Exception e) {
            throw new CacheException("Error serializing object.  Cause: " + e, e);
        }
    }

    private Serializable deserialize(byte[] value) {
        Serializable result;
        try {
            //反序列化核心就是ByteArrayInputStream
            ByteArrayInputStream bis = new ByteArrayInputStream(value);
            ObjectInputStream ois = new ObjectInputStream(bis);
            result = (Serializable) ois.readObject();
            ois.close();
        } catch (Exception e) {
            throw new CacheException("Error deserializing object.  Cause: " + e, e);
        }
        return result;
    }

}
