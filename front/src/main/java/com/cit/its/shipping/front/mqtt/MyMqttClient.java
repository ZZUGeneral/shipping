package com.cit.its.shipping.front.mqtt;

import com.cit.its.shipping.front.common.Const;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * MQTT Client
 */
@Slf4j
@UtilityClass
public class MyMqttClient {

    private MqttClient sampleClient;

    private void subscribe(String[] topics) throws MqttException {
        sampleClient.subscribe(topics);
    }

    public void connect(SimpMessagingTemplate template) throws MqttException {
        if (sampleClient == null) {
            sampleClient = new MqttClient(Const.MQTT_BROKER, Const.BACKEND_CLIENT, null);
        }
        if (sampleClient.isConnected()) {
            return ;
        }
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        sampleClient.connect(connOpts);
        sampleClient.setCallback(new MyMqttCallback(template));
//        log.info("mqtt client connected !");
        String[] topics = {Const.TOPIC_SYS_CLIENT_CONNECTED, Const.TOPIC_SYS_CLIENT_DISCONNECTED, Const.TOPIC_WATER_LEVEL_ALL,
                Const.TOPIC_TILE_ALL, Const.TOPIC_ANGLE_ALL, Const.TOPIC_VIBRATION_ALL, Const.TOPIC_WEATHER_ALL};
        subscribe(topics);
    }

    private void disconnect() throws MqttException {
        if (sampleClient != null && sampleClient.isConnected()) {
            sampleClient.disconnect();
            log.info("mqtt client disconnected !");
        }
    }

}
