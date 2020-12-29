package pers.dyx.tool.mqtt;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author: dyx
 * @date: 2018/8/7 09:01
 * @description: 订阅回调
 */
public class SubscribeCallback implements MqttCallback {

    private static final Logger logger = Logger.getLogger(SubscribeCallback.class);

    @Override
    public void connectionLost(Throwable cause) {
        logger.info("连接丢失");
        ReConnectUtil reConnectUtil = new ReConnectUtil();
        while (!MqttExecutor.getInstance().isConnected()) {
            try {
                long time = (long) reConnectUtil.randomConnectPeriod();
                logger.info(time + "等待毫秒后重新连接");
                Thread.sleep(time);
            } catch (InterruptedException e) {
                continue;
            }
            if (ReConnectUtil.getInetStatus()) {
                MqttExecutor.getInstance().connect();
            }
        }
        MqttExecutor.getInstance().subscribe(new String[]{"topic"}, new SubscribeCallback());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        logger.info("topic: " + topic + "  接收的消息: " + message);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info(token.getTopics());
    }
}
