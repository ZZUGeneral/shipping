package com.cit.its.shipping.front.observer;


import com.cit.its.shipping.front.RealTime.WaterLevelRealTime;
import com.cit.its.shipping.front.common.WaterLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 *@Author: 黄贵生
 *@Description: 水位传感器数据消息观察者注册类，用于注册不同的水位传感器数据处理方法
*/
public class WaterLevelObserver {

    @Autowired
    private WaterLevelRealTime wl1 = new WaterLevelRealTime();
    @Autowired
    private WaterLevelRealTime wl2 = new WaterLevelRealTime();
    @Autowired
    private WaterLevelRealTime wl3 = new WaterLevelRealTime();
    @Autowired
    private WaterLevelRealTime wl4 = new WaterLevelRealTime();

    /**
     *@Author: 黄贵生
     *@Description: 注册方法，根据topic自动分发数据消息来处理不同的传感器数据
     *@Param:
     *@return:
    */
    public void registWaterLevel(){

        MyNotifier notifier = Notify.getNotifier();
        notifier.subscribe("get/waterLevel/w1", new EventObserver() {
            @Override
            public String onEvent(Object info) throws IOException {
                System.out.println("info = " + "w1");
                ObjectMapper mapper = new ObjectMapper();
                WaterLevel waterLevel = mapper.readValue((String) info, WaterLevel.class);
                return wl1.waterLevelProcess(waterLevel);
            }
        });

        notifier.subscribe("get/waterLevel/w2", new EventObserver() {
            @Override
            public String onEvent(Object info) throws IOException {
                System.out.println("info = " + "w2");
                ObjectMapper mapper = new ObjectMapper();
                WaterLevel waterLevel = mapper.readValue((String) info, WaterLevel.class);
                return wl2.waterLevelProcess(waterLevel);
            }
        });

        notifier.subscribe("get/waterLevel/w3", new EventObserver() {
            @Override
            public String onEvent(Object info) throws IOException {
                System.out.println("info = " + "w3");
                ObjectMapper mapper = new ObjectMapper();
                WaterLevel waterLevel = mapper.readValue((String) info, WaterLevel.class);
                return wl3.waterLevelProcess(waterLevel);
            }
        });

        notifier.subscribe("get/waterLevel/w4", new EventObserver() {
            @Override
            public String onEvent(Object info) throws IOException {
                System.out.println("info = " + "w4");
                ObjectMapper mapper = new ObjectMapper();
                WaterLevel waterLevel = mapper.readValue((String) info, WaterLevel.class);
                return wl4.waterLevelProcess(waterLevel);
            }
        });
    }
}
