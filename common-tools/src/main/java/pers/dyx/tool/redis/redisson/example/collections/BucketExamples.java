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
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

public class BucketExamples {

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://10.110.1.216:6280").setDatabase(5).setPassword("zjcs@1qaz@WSX");
        RedissonClient redisson = Redisson.create(config);


        RBucket<String> bucket = redisson.getBucket("test1");
        boolean isUpdated = bucket.compareAndSet("123", "4934");
        String prevObject = bucket.getAndSet("321");
        boolean isSet = bucket.trySet("901");
        long objectSize = bucket.size();

        // set with expiration
        bucket.set("value", 10, TimeUnit.HOURS);
        boolean isNewSet = bucket.trySet("nextValue", 10, TimeUnit.SECONDS);

        redisson.shutdown();
    }

}
