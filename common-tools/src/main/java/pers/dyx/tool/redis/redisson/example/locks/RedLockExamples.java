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
package pers.dyx.tool.redis.redisson.example.locks;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class RedLockExamples {

    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.2.188:16379").setPassword("anguikaoshi").setDatabase(5);
        RedissonClient client1 = Redisson.create(config);

        RLock lock1 = client1.getLock("lock6");
        RedissonMultiLock lock = new RedissonRedLock(lock1);
        boolean isLock = lock.tryLock(1, 3000000, TimeUnit.SECONDS);
        lock.lock(-1, TimeUnit.MINUTES);
        lock.unlock();
        client1.shutdown();
    }

}
