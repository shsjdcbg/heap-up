package pers.dyx.tool.mqtt;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.UUID;

/**
 * Description：MQTT执行器
 *
 * @author dyx
 * @date 2019/2/15 14:51
 */
public class MqttExecutor {
    private static final Logger logger = Logger.getLogger(MqttExecutor.class);
    private MqttClient mqttClient;
    private MqttConnectOptions options;
    private String brokerUrl;
    private String brokerLoginName;
    private String brokerPassword;

    private static class Holder {
        private static MqttExecutor instance = new MqttExecutor(
                "tcp://10.110.1.216:6480",
                "zjcs",
                "zjcs@1qaz@WSX"
        );
    }

    public static MqttExecutor getInstance() {
        return Holder.instance;
    }

    private MqttExecutor(String brokerUrl, String brokerLoginName, String brokerPassword) {
        this.brokerUrl = brokerUrl;
        this.brokerLoginName = brokerLoginName;
        this.brokerPassword = brokerPassword;
        connect();
    }

    /**
     * 连接
     */
    protected void connect() {
        try {
            String clientId = "intermediary-tomcat" + UUID.randomUUID();
            if (mqttClient == null) {
                mqttClient = new MqttClient(brokerUrl, clientId);
            }
            options = new MqttConnectOptions();
            options.setUserName(brokerLoginName);
            options.setPassword(brokerPassword.toCharArray());
            X509TrustManager x509m = new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {
                }
            };
            // 获取一个SSLContext实例
            SSLContext s = SSLContext.getInstance("SSL");
            // 初始化SSLContext实例
            s.init(null, new TrustManager[]{x509m},
                    new java.security.SecureRandom());
//            String caPath = URLDecoder.decode(this.getClass().getClassLoader().getResource("cacert.crt").toString(), "utf-8");
//            String crtPath = URLDecoder.decode(this.getClass().getClassLoader().getResource("client-cert.crt").toString(), "utf-8");
//            String keyPath = URLDecoder.decode(this.getClass().getClassLoader().getResource("client-key-pkcs8.pem").toString(), "utf-8");
//            SSLSocketFactory sslSocketFactory = SSLSocket.getSSLSocktet(caPath.substring(6,caPath.length()),
//                    crtPath.substring(6, crtPath.length()), keyPath.substring(6, keyPath.length()), "brt123");
//            options.setSocketFactory(sslSocketFactory);
//            options.setSocketFactory(s.getSocketFactory());
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setKeepAliveInterval(30);
            if (!mqttClient.isConnected()) {
                mqttClient.connect(options);
            } else {
                mqttClient.disconnect();
                mqttClient.connect(options);
            }
            logger.info("连接MQTT Server：" + brokerUrl);
        } catch (MqttException e) {
            logger.info("连接MQTT Server：" + brokerUrl + "失败：" + e.getMessage());
            logger.error(e.getMessage());
            connect();
        } catch (NoSuchAlgorithmException e) {
            logger.info("连接MQTT Server：" + brokerUrl + "失败" + e.getMessage());
            logger.error(e.getMessage());
            connect();
        } catch (KeyManagementException e) {
            logger.info("连接MQTT Server：" + brokerUrl + "失败" + e.getMessage());
            logger.error(e.getMessage());
            connect();
        }
    }

    /**
     * 连接状态
     *
     * @return
     */
    protected boolean isConnected() {
        return mqttClient.isConnected();
    }

    /**
     * 订阅
     *
     * @param topics       主题数组
     * @param mqttCallback Callback 类
     */
    public void subscribe(String[] topics, MqttCallback mqttCallback) {
        try {
            if (!isConnected()) {
                connect();
            }
            if (mqttCallback != null) {
                mqttClient.setCallback(mqttCallback);
            }
            int[] qos = new int[topics.length];
            for (int i = 0; i < qos.length; i++) {
                qos[i] = 2;
            }
            IMqttToken result = mqttClient.subscribeWithResponse(topics, qos);
            String topic = "";
            for (String temp : topics) {
                topic += " " + temp;
            }
            logger.info("已订阅主题：" + topic + " " + result.getResponse());
        } catch (MqttException e) {
            logger.error("订阅主题失败" + e.getMessage());
            subscribe(topics, mqttCallback);
        }
    }

    /**
     * 订阅
     *
     * @param topic        主题
     * @param mqttCallback Callback 类
     */
    public void subscribe(String topic, MqttCallback mqttCallback) {
        try {
            if (!isConnected()) {
                connect();
            }
            if (mqttCallback != null) {
                mqttClient.setCallback(mqttCallback);
            }
            IMqttToken result = mqttClient.subscribeWithResponse(topic);
            logger.info("已订阅主题：" + topic + " " + result.getResponse());
        } catch (MqttException e) {
            logger.error("已订阅主题失败" + e.getMessage());
            subscribe(topic, mqttCallback);
        }
    }

    /**
     * 退订
     *
     * @param topics 主题数组
     */
    public void unsubscribe(String[] topics) {
        try {
            if (isConnected()) {
                mqttClient.unsubscribe(topics);
                String topic = "";
                for (String temp : topics) {
                    topic += " " + temp;
                }
                logger.info("已退订主题：" + topic);
            }
        } catch (MqttException e) {
            logger.error(e.getMessage());
            unsubscribe(topics);
        }
    }

    /**
     * 退订
     *
     * @param topic 主题
     */
    public void unsubscribe(String topic) {
        try {
            if (isConnected()) {
                mqttClient.unsubscribe(topic);
                logger.info("已退订主题：" + topic);
            }
        } catch (MqttException e) {
            logger.error(e.getMessage());
            unsubscribe(topic);
        }
    }

    /**
     * 发布
     *
     * @param topic   主题
     * @param message 消息
     */
    public void publish(String topic, String message) throws MqttException {
        publish(topic, message, 2, false);
    }

    /**
     * 发布
     *
     * @param topic    主题
     * @param message  消息
     * @param qos      服务质量
     * @param retained 是否离线保留消息
     */
    public void publish(String topic, String message, int qos, boolean retained) throws MqttException {
        if (!isConnected()) {
            connect();
        }
        MqttTopic mqttTopic = mqttClient.getTopic(topic);
        mqttTopic.publish(message.getBytes(), qos, retained);
        logger.info("Sending Topic：" + topic + "  消息" + message);
    }

    /**
     * 关闭
     */
    public void close() {
        try {
            if (mqttClient.isConnected()) {
                mqttClient.disconnect();
                mqttClient.close();
            }
        } catch (MqttException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    private void disconnect() {
        try {
            if (mqttClient.isConnected()) {
                mqttClient.disconnect();
            }
        } catch (MqttException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MqttException {
        MqttExecutor.getInstance().publish("intermediary/12/12/13", "消息1");
    }
}
