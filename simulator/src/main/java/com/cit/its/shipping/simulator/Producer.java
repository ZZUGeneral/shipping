package com.cit.its.shipping.simulator;

import com.cit.its.shipping.simulator.consts.Const;
import com.cit.its.shipping.simulator.message.*;
import com.cit.its.shipping.simulator.mqtt.ClientFactory;
import com.cit.its.shipping.simulator.mqtt.SendTask;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import java.util.Timer;

@Slf4j
public class Producer {

    public static void main(String[] args) {
       waterLevel();
        tilt();
        angle();
        vibration();
        weather();
    }


    public static void waterLevel() {
        Timer timer = new Timer();
        WaterLevelMessage waterLevelMessage = new WaterLevelMessage();
        try {
            MqttClient wlc1 = ClientFactory.getClient(Const.CLIENT_ID_W1);
            MqttClient wlc2 = ClientFactory.getClient(Const.CLIENT_ID_W2);
            MqttClient wlc3 = ClientFactory.getClient(Const.CLIENT_ID_W3);
            MqttClient wlc4 = ClientFactory.getClient(Const.CLIENT_ID_W4);
            timer.schedule(new SendTask(wlc1, waterLevelMessage, Const.TOPIC_W1), 0, 3000);
            timer.schedule(new SendTask(wlc2, waterLevelMessage, Const.TOPIC_W2), 0, 3000);
            timer.schedule(new SendTask(wlc3, waterLevelMessage, Const.TOPIC_W3), 0, 3000);
            timer.schedule(new SendTask(wlc4, waterLevelMessage, Const.TOPIC_W4), 0, 3000);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void tilt() {
        Timer timer = new Timer();
        TiltMessage tiltMessage = new TiltMessage();
        try {
            MqttClient wlc1 = ClientFactory.getClient(Const.CLIENT_ID_T1);
            MqttClient wlc2 = ClientFactory.getClient(Const.CLIENT_ID_T2);
            MqttClient wlc3 = ClientFactory.getClient(Const.CLIENT_ID_T3);
            MqttClient wlc4 = ClientFactory.getClient(Const.CLIENT_ID_T4);
            MqttClient wlc5 = ClientFactory.getClient(Const.CLIENT_ID_T5);
            MqttClient wlc6 = ClientFactory.getClient(Const.CLIENT_ID_T6);
            MqttClient wlc7 = ClientFactory.getClient(Const.CLIENT_ID_T7);
            MqttClient wlc8 = ClientFactory.getClient(Const.CLIENT_ID_T8);
            timer.schedule(new SendTask(wlc1, tiltMessage, Const.TOPIC_T1), 0, 3000);
            timer.schedule(new SendTask(wlc2, tiltMessage, Const.TOPIC_T2), 0, 3000);
            timer.schedule(new SendTask(wlc3, tiltMessage, Const.TOPIC_T3), 0, 3000);
            timer.schedule(new SendTask(wlc4, tiltMessage, Const.TOPIC_T4), 0, 3000);
            timer.schedule(new SendTask(wlc5, tiltMessage, Const.TOPIC_T5), 0, 3000);
            timer.schedule(new SendTask(wlc6, tiltMessage, Const.TOPIC_T6), 0, 3000);
            timer.schedule(new SendTask(wlc7, tiltMessage, Const.TOPIC_T7), 0, 3000);
            timer.schedule(new SendTask(wlc8, tiltMessage, Const.TOPIC_T8), 0, 3000);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void angle() {
        Timer timer = new Timer();
        AngleMessage angleMessage = new AngleMessage();
        try {
            MqttClient mqttClient1 = ClientFactory.getClient(Const.CLIENT_ID_A1);
            MqttClient mqttClient2 = ClientFactory.getClient(Const.CLIENT_ID_A2);
            MqttClient mqttClient3 = ClientFactory.getClient(Const.CLIENT_ID_A3);
            MqttClient mqttClient4 = ClientFactory.getClient(Const.CLIENT_ID_A4);
            timer.schedule(new SendTask(mqttClient1, angleMessage, Const.TOPIC_A1), 0, 3000);
            timer.schedule(new SendTask(mqttClient2, angleMessage, Const.TOPIC_A2), 0, 3000);
            timer.schedule(new SendTask(mqttClient3, angleMessage, Const.TOPIC_A3), 0, 3000);
            timer.schedule(new SendTask(mqttClient4, angleMessage, Const.TOPIC_A4), 0, 3000);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void vibration() {
        Timer timer = new Timer();
        VibrationMessage vibrationMessage = new VibrationMessage();
        try {
            MqttClient mqttClient1 = ClientFactory.getClient(Const.CLIENT_ID_V1);
            MqttClient mqttClient2 = ClientFactory.getClient(Const.CLIENT_ID_V2);
            MqttClient mqttClient3 = ClientFactory.getClient(Const.CLIENT_ID_V3);
            MqttClient mqttClient4 = ClientFactory.getClient(Const.CLIENT_ID_V4);
            timer.schedule(new SendTask(mqttClient1, vibrationMessage, Const.TOPIC_V1), 0, 3000);
            timer.schedule(new SendTask(mqttClient2, vibrationMessage, Const.TOPIC_V2), 0, 3000);
            timer.schedule(new SendTask(mqttClient3, vibrationMessage, Const.TOPIC_V3), 0, 3000);
            timer.schedule(new SendTask(mqttClient4, vibrationMessage, Const.TOPIC_V4), 0, 3000);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public static void weather() {
        Timer timer = new Timer();
        WeatherGeneralMessage weatherGeneralMessage = new WeatherGeneralMessage();
        WeatherRainfallMessage weatherRainfallMessage = new WeatherRainfallMessage();
        WeatherVisibilityMessage weatherVisibilityMessage = new WeatherVisibilityMessage();
        try {
            MqttClient wlc1 = ClientFactory.getClient(Const.CLIENT_ID_WEATHER1);
            MqttClient wlc2 = ClientFactory.getClient(Const.CLIENT_ID_WEATHER2);
            MqttClient wlc3 = ClientFactory.getClient(Const.CLIENT_ID_WEATHER3);
            timer.schedule(new SendTask(wlc1, weatherGeneralMessage, Const.TOPIC_WEATHER1), 0, 3000);
            timer.schedule(new SendTask(wlc2, weatherRainfallMessage, Const.TOPIC_WEATHER2), 0, 3000);
            timer.schedule(new SendTask(wlc3, weatherVisibilityMessage, Const.TOPIC_WEATHER3), 0, 3000);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
