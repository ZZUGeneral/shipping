package com.cit.its.shipping.front;

import com.cit.its.shipping.front.mqtt.MyMqttClient;
import com.cit.its.shipping.front.observer.WaterLevelObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void run(String... args) throws Exception {
        MyMqttClient myMqttClient = null;
        myMqttClient.connect(template);

        WaterLevelObserver wlo = new WaterLevelObserver();
        wlo.registWaterLevel();
    }
}
