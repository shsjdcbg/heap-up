package pers.dyx.tool.localcache;

import org.junit.Test;
import pers.dyx.tool.localcache.ICache;
import pers.dyx.tool.localcache.PerpetualCache;

import static org.junit.Assert.*;

/**
 * @author dyx
 * @date 2020/9/415:29
 */
public class PerpetualCacheTest {

    @Test
    public void shouldDemonstrateHowAllObjectsAreKept() {
        ICache cache = new PerpetualCache("default");
        for (int i = 0; i < 100000; i++) {
            cache.put(i, i);
            assertEquals(i, cache.get(i));
        }
        assertEquals(100000, cache.getSize());
    }

    @Test
    public void shouldDemonstrateCopiesAreEqual() {
        ICache cache = new PerpetualCache("default");
        for (int i = 0; i < 1000; i++) {
            cache.put(i, i);
            assertEquals(i, cache.get(i));
        }
    }

    @Test
    public void shouldRemoveItemOnDemand() {
        ICache cache = new PerpetualCache("default");
        cache.put(0, 0);
        assertNotNull(cache.get(0));
        cache.remove(0);
        assertNull(cache.get(0));
    }

    @Test
    public void shouldFlushAllItemsOnDemand() {
        ICache cache = new PerpetualCache("default");
        for (int i = 0; i < 5; i++) {
            cache.put(i, i);
        }
        assertNotNull(cache.get(0));
        assertNotNull(cache.get(4));
        cache.clear();
        assertNull(cache.get(0));
        assertNull(cache.get(4));
    }
}
