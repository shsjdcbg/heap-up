/**
 * Copyright 2016 Nikita Koksharov
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pers.dyx.tool.redis.redisson.example.collections;

import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;

import java.io.IOException;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;

public class MapExamples {

    public static void main(String[] args) throws IOException {
        // connects to 127.0.0.1:6379 by default
        Config config = new Config();
        String[] addresses = "redis://10.110.1.216:26379,redis://10.110.1.216:26380,redis://10.110.1.216:26381".split(",");
        SentinelServersConfig sentinelServersConfig = config.useSentinelServers().setMasterName("mymaster");
        for (String address : addresses) {
            sentinelServersConfig.addSentinelAddress(address);
        }
        RedissonClient redisson = Redisson.create(config);

        RMap<String, String> map = redisson.getMap("signup_23b4162783cd482f8eb6c2d912189107");
//        System.out.println(map.getName());
//        map.put("title", "中文标题");
//        map.putAsync("price", "123");
//
//        boolean contains = map.containsKey("a");
//
//        String value = map.get("title");
//        System.out.println(value);
////        String updatedValue = map.addAndGet("a", 32);
//
//        Set<String> keys = new HashSet<String>();
//        keys.add("a");
//        keys.add("b");
//        keys.add("c");
//        Map<String, String> mapSlice = map.getAll(keys);

        // use read* methods to fetch all objects
        Set<String> allKeys = map.readAllKeySet();
        Collection<String> allValues = map.readAllValues();
        Set<Entry<String, String>> allEntries = map.readAllEntrySet();

        // use fast* methods when previous value is not required
//        boolean isNewKey = map.fastPut("a", "1000");
//        boolean isNewKeyPut = map.fastPutIfAbsent("d", "23");
//        long removedAmount = map.fastRemove("b");

        redisson.shutdown();
    }

}
