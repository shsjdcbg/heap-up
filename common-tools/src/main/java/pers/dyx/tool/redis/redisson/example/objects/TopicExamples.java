/**
 * Copyright 2016 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pers.dyx.tool.redis.redisson.example.objects;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.api.listener.MessageListener;
import org.redisson.codec.SerializationCodec;
import org.redisson.config.Config;

public class TopicExamples {

    public static void main(String[] args)  {

        Config config = new Config();
        config.useMasterSlaveServers()
                //可以用"rediss://"来启用SSL连接
                .setMasterAddress("redis://10.110.5.14:6380")
                .addSlaveAddress("redis://10.110.5.14:6381", "redis://10.110.5.14:6382");

        RedissonClient redisson = Redisson.create(config);

        try{
            RTopic topic = redisson.getTopic("dw",new SerializationCodec());
            topic.addListener(String.class, new MessageListener<String>() {
                @Override
                public void onMessage(CharSequence charSequence, String msg) {
                    System.out.println("onMessage:"+charSequence+"; Thread: "+Thread.currentThread().toString());
                    System.out.println(msg);
                }
            });
            topic.publish("");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {

        }
    }

}
