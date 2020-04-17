package com.cit.its.shipping.front.mqtt;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.nio.charset.Charset;

/**
 * MQTT 消息处理
 */
@Slf4j
public class MyMqttCallback implements MqttCallback {

    private SimpMessagingTemplate template;

    public MyMqttCallback(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Override
    public void connectionLost(Throwable throwable) {
//        log.error("mqtt client 连接断开 ！", throwable);
    }

    /**
     * 接收消息，并转发到STOMP
     * @param topic
     * @param mqttMessage
     * @throws Exception
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        byte[] payload = mqttMessage.getPayload();
        String jsonContent = new String(payload, Charset.forName("UTF-8"));
        if (StrUtil.endWith(topic, "connected") || StrUtil.endWith(topic, "disconnected")) {
            template.convertAndSend("/realtime/sensor", "");
        } else {
            template.convertAndSend("/realtime/" + topic, jsonContent);
        }
        //log.info("mqtt 接收到新消息 ---->  topic : {} ------> content : {}",topic, jsonContent);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

}
