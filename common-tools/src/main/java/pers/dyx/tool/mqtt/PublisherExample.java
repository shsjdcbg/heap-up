package pers.dyx.tool.mqtt;

import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author: dyx
 * @date: 2018/8/7 15:04
 * @description:
 */
public class PublisherExample {

    public static void main(String[] args) {
        MqttExecutor publisher = MqttExecutor.getInstance();
        publisher.connect();
        try {
            publisher.publish("intermediary/job/updateCountdown/23262", "21345");
        } catch (MqttException e) {
            e.printStackTrace();
        } finally {
            publisher.close();
        }
    }
}
