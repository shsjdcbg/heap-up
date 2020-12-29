package pers.dyx.tool.mqtt;

/**
 * @author: dyx
 * @date: 2018/8/7 09:47
 * @description:
 */
public class SubscriberExample {

    public static void main(String[] args) {
        MqttExecutor subscriber = MqttExecutor.getInstance();
        subscriber.connect();
        subscriber.subscribe(new String[]{"intermediary/+/#"}, new SubscribeCallback());
    }
}
