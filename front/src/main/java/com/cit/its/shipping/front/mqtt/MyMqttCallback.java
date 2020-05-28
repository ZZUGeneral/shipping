package com.cit.its.shipping.front.mqtt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.cit.its.shipping.front.dao.WaterLevelMapper;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.WaterLevelService;
import com.cit.its.shipping.front.util.SpringContextUtil;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import springfox.documentation.spring.web.json.Json;

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
     *
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
            insertMessage(topic, jsonContent);
        }
        //log.info("mqtt 接收到新消息 ---->  topic : {} ------> content : {}", topic, jsonContent);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    public void insertMessage(String topic, String jsonContent) {
        if (StrUtil.startWith(topic, "get/waterLevel/")) {
            WaterLevelService waterLevelService = SpringContextUtil.getBean(WaterLevelService.class);
            waterLevelService.insertWaterLevel(topic, jsonContent);
        } else if (StrUtil.startWith(topic, "get/tilt/")) {

        } else if (StrUtil.startWith(topic, "get/angle/")) {

        } else if (StrUtil.startWith(topic, "get/vibration/")) {

        } else if (StrUtil.startWith(topic, "get/weather/general")) {

        } else if (StrUtil.startWith(topic, "get/weather/rainfall")) {

        } else if (StrUtil.startWith(topic, "get/weather/visibility")) {

        }
    }
}
