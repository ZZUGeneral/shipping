package com.cit.its.shipping.front.mqtt;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.cit.its.shipping.front.dao.WaterLevelMapper;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.*;
import com.cit.its.shipping.front.util.SpringContextUtil;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.StringUtil;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
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
        System.out.println("===================" + topic);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++"+StrUtil.endWith(topic, "disconnected"));
        if (StrUtil.endWith(topic, "connected") || StrUtil.endWith(topic, "disconnected")) {
            System.out.println("=========================================================");
            updateClient(topic, jsonContent);

            template.convertAndSend("/realtime/sensor", "");
        } else {
            template.convertAndSend("/realtime/" + topic, jsonContent);
            insertMessage(topic, jsonContent);
        }
        log.info("mqtt 接收到新消息 ---->  topic : {} ------> content : {}", topic, jsonContent);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    }

    public void insertMessage(String topic, String jsonContent) {
        if (StrUtil.startWith(topic, "get/waterLevel/")) {
            WaterLevelService waterLevelService = SpringContextUtil.getBean(WaterLevelService.class);
            waterLevelService.insertWaterLevel(topic, jsonContent);
        } else if (StrUtil.startWith(topic, "get/tilt/")) {
            TiltService tiltService = SpringContextUtil.getBean(TiltService.class);
            tiltService.insertTilt(topic, jsonContent);
        } else if (StrUtil.startWith(topic, "get/angle/")) {
            AngleService angleService = SpringContextUtil.getBean(AngleService.class);
            angleService.insertAngle(topic, jsonContent);
        } else if (StrUtil.startWith(topic, "get/vibration/")) {
            VibrationService vibrationService = SpringContextUtil.getBean(VibrationService.class);
            vibrationService.insertVibration(topic, jsonContent);
        } else if (StrUtil.startWith(topic, "get/weather/general")) {
            WeatherGeneralService weatherGeneralService = SpringContextUtil.getBean(WeatherGeneralService.class);
            weatherGeneralService.insertWeatherGeneral(topic, jsonContent);
        } else if (StrUtil.startWith(topic, "get/weather/rainfall")) {
            WeatherRainfallService weatherRainfallService = SpringContextUtil.getBean(WeatherRainfallService.class);
            weatherRainfallService.insertWeatherRainfall(topic, jsonContent);
        } else if (StrUtil.startWith(topic, "get/weather/visibility")) {
            WeatherVisibilityService weatherVisibilityService = SpringContextUtil.getBean(WeatherVisibilityService.class);
            weatherVisibilityService.insertWeatherVisibility(topic, jsonContent);
        }
    }

    public void updateClient(String topic, String jsonContent) {
        // topic : $SYS/brokers/emqx@127.0.0.1/clients/simulator_client_water_level_w4/connected ------> content : {"username":"undefined","ts":1590667457818,"sockport":1883,"proto_ver":4,"proto_name":"MQTT","keepalive":60,"ipaddress":"127.0.0.1","expiry_interval":0,"connected_at":1590667457,"connack":0,"clientid":"simulator_client_water_level_w4","clean_start":true}
        //  topic : $SYS/brokers/emqx@127.0.0.1/clients/simulator_client_weather_rainfall/disconnected ------> content : {"username":"undefined","ts":1590667565084,"reason":"tcp_closed","disconnected_at":1590667565,"clientid":"simulator_client_weather_rainfall"}
        ClientService clientService = SpringContextUtil.getBean(ClientService.class);
        System.out.println("===========---------------------------------------------------------------------");
        clientService.updateClientState(topic, jsonContent);
    }
}
