package com.cit.its.shipping.simulator.mqtt;

import com.cit.its.shipping.simulator.consts.Const;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

public class ClientFactory {

    public static MqttClient getClient(String clientId) throws MqttException {
        MqttClient client = new MqttClient(Const.BROKER, clientId, null);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        client.connect(options);
        return client;
    }

}
