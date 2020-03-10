package com.cit.its.shipping.simulator.mqtt;

import com.cit.its.shipping.simulator.consts.Const;
import com.cit.its.shipping.simulator.message.AbstractMessage;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.TimerTask;

public class SendTask extends TimerTask {

    private MqttClient client;
    private AbstractMessage message;
    private String topic;

    public SendTask(MqttClient client, AbstractMessage message, String topic) {
        this.client = client;
        this.message = message;
        this.topic = topic;
    }

    @Override
    public void run() {
        try {
            MqttMessage msg = new MqttMessage(message.msgContent());
            msg.setQos(Const.DEFAULT_QOS);
            client.publish(topic, msg);
            System.out.println(client.getClientId() + "\t发送数据：" + message.toString());
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
