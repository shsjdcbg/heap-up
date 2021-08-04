package pers.dyx.event;

import org.springframework.context.ApplicationEvent;

/**
 * 定义一个事件
 *
 * @author dyx
 * @date 2021/7/22 15:27
 */
public class EventDemo extends ApplicationEvent {
    private String message;

    public EventDemo(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
