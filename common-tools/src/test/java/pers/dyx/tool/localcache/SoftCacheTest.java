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
import pers.dyx.tool.localcache.PerpetualCache;
import pers.dyx.tool.localcache.SerializedCache;
import pers.dyx.tool.localcache.SoftCache;

import static org.junit.Assert.*;

public class SoftCacheTest {

    @Test
    public void shouldDemonstrateObjectsBeingCollectedAsNeeded() throws Exception {
        final int N = 3000000;
        SoftCache cache = new SoftCache(new PerpetualCache("default"));
        for (int i = 0; i < N; i++) {
            byte[] array = new byte[5001]; //waste a bunch of memory
            array[5000] = 1;
            cache.put(i, array);
            Object value = cache.get(i);
            if (cache.getSize() < i + 1) {
                //System.out.println("Cache exceeded with " + (i + 1) + " entries.");
                break;
            }
        }
        assertTrue(cache.getSize() < N);
    }


    @Test
    public void shouldDemonstrateCopiesAreEqual() {
        ICache cache = new SoftCache(new PerpetualCache("default"));
        cache = new SerializedCache(cache);
        for (int i = 0; i < 1000; i++) {
            cache.put(i, i);
            Object value = cache.get(i);
            assertTrue(value == null || value.equals(i));
        }
    }

    @Test
    public void shouldRemoveItemOnDemand() {
        ICache cache = new SoftCache(new PerpetualCache("default"));
        cache.put(0, 0);
        assertNotNull(cache.get(0));
        cache.remove(0);
        assertNull(cache.get(0));
    }

    @Test
    public void shouldFlushAllItemsOnDemand() {
        ICache cache = new SoftCache(new PerpetualCache("default"));
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