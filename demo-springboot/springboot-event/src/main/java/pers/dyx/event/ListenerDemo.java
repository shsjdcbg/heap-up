package pers.dyx.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 我们可以不用实现 AppplicationListener 接口 ，在方法上使用 @EventListener 注册事件。如果你的方法应该侦听多个事件，并不使用任何参数来定义，可以在 @EventListener 注解上指定多个事件。
 *
 * @author dyx
 * @date 2021/7/22 16:05
 */
@Component
public class ListenerDemo {
    @EventListener()
    public void processApplicationEvent(EventDemo event) {
        String msg = event.getMessage();
        System.out.println("bean-listener 收到了 publisher 发布的消息: " + msg);
    }
}
