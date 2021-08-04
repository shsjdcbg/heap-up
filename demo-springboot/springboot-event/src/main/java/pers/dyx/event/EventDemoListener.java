package pers.dyx.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 定义一个事件监听者
 *
 * @author dyx
 * @date 2021/7/22 15:29
 */
@Component
public class EventDemoListener implements ApplicationListener<EventDemo> {
    @Override
    public void onApplicationEvent(EventDemo event) {
        System.out.println("receiver " + event.getMessage());
    }
}
