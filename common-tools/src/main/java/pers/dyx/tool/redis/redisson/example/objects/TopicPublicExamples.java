package pers.dyx.tool.redis.redisson.example.objects;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;

/**
 * Description：
 *
 * @author dyx
 * @date 2019/2/14 09:18
 */
public class TopicPublicExamples {

    public static void main(String[] args) throws InterruptedException {
        // connects to 127.0.0.1:6379 by default
        Config config = new Config();
        config.useMasterSlaveServers()
                //可以用"rediss://"来启用SSL连接
                .setMasterAddress("redis://10.110.5.14:6380")
                .addSlaveAddress("redis://10.110.5.14:6381", "redis://10.110.5.14:6382");

        RedissonClient redisson = Redisson.create(config);
        try {
            RTopic topic = redisson.getTopic("dw", new SerializationCodec());
            int i = 0;
            while (true) {
                topic.publish("hi" + i++);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {

        }
    }

}
