/*
 *    Copyright 2009-2012 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package pers.dyx.tool.localcache;

import org.junit.Test;
import pers.dyx.tool.localcache.ICache;
import pers.dyx.tool.localcache.LruCache;
import pers.dyx.tool.localcache.PerpetualCache;

import static org.junit.Assert.*;

public class LruCacheTest {

    @Test
    public void shouldRemoveLeastRecentlyUsedItemInBeyondFiveEntries() {
        LruCache cache = new LruCache(new PerpetualCache("default"));
        cache.setSize(5);
        for (int i = 0; i < 5; i++) {
            cache.put(i, i);
        }
        assertEquals(0, cache.get(0));
        cache.put(5, 5);
        assertNull(cache.get(1));
        assertEquals(5, cache.getSize());
    }

    @Test
    public void shouldRemoveItemOnDemand() {
        ICache cache = new LruCache(new PerpetualCache("default"));
        cache.put(0, 0);
        assertNotNull(cache.get(0));
        cache.remove(0);
        assertNull(cache.get(0));
    }

    @Test
    public void shouldFlushAllItemsOnDemand() {
        ICache cache = new LruCache(new PerpetualCache("default"));
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