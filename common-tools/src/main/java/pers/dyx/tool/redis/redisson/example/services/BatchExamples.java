package pers.dyx.tool.redis.redisson.example.services;

import org.redisson.Redisson;
import org.redisson.api.RBatch;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * Description：
 *
 * @author dyx
 * @date 2019/9/6 09:45
 */
public class BatchExamples {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://10.110.1.216:6280").setDatabase(5).setPassword("zjcs@1qaz@WSX");
        RedissonClient redisson = Redisson.create(config);
        RBatch batch = redisson.createBatch();
        batch.getSet("setkey").readAllAsync();
        batch.getList("listkey").readAllAsync();
        batch.getMap("hashkey").addAndGetAsync("hashfiled", 2);
        batch.getAtomicDouble("key1");
        batch.getAtomicLong("key2");
        batch.getBitSet("key3");
    }
}
