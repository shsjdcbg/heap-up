package pers.dyx.event;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.dyx.DemoApplication;

/**
 * @author dyx
 * @date 2021/7/22 15:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DemoApplication.class}) // 指定启动类
public class EventDemoPublishTest {

    @Autowired
    private EventDemoPublish demoPublisher;

    @Test
    public void publish() {
        demoPublisher.publish("消息！");
    }
}
