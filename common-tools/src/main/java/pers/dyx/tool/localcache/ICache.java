package pers.dyx.tool.localcache;

/**
 * 缓存接口
 *
 * @author dyx
 * @date 2020/9/413:39
 */
public interface ICache {

    /**
     * 获取ID
     *
     * @return ID字符串
     */
    String getId();

    /**
     * 存入键值
     *
     * @param key   键
     * @param value 值
     */
    void put(Object key, Object value);

    /**
     * 根据键获取值
     *
     * @param key 键
     * @return 值
     */
    Object get(Object key);

    /**
     * 删除值
     *
     * @param key 键
     * @return 值
     */
    Object remove(Object key);

    /**
     * 清空
     */
    void clear();

    /**
     * 获取大小
     *
     * @return 大小
     */
    int getSize();

}
