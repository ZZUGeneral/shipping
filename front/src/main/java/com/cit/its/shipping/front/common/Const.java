package com.cit.its.shipping.front.common;

/**
 * 系统常量
 */
public class Const {
    /** EMQ broker **/
    public static final String MQTT_BROKER = "tcp://127.0.0.1:1883";
    /** 默认QOS **/
    public static final int DEFAULT_QOS = 1;
    /** 代码终端ClientId **/
    public static final String BACKEND_CLIENT = "backend_client";
    /** 系统主题-终端上线 **/
    public static final String TOPIC_SYS_CLIENT_CONNECTED = "$SYS/brokers/+/clients/+/connected";
    /** 系统主题-终端下线 **/
    public static final String TOPIC_SYS_CLIENT_DISCONNECTED = "$SYS/brokers/+/clients/+/disconnected";
    /** 业务主题-全部水位消息 **/
    public static final String TOPIC_WATER_LEVEL_ALL = "get/waterLevel/+";
    /** 业务主题-全部倾斜消息 **/
    public static final String TOPIC_TILE_ALL = "get/tilt/+";
    /** 业务主题-全部角度消息 **/
    public static final String TOPIC_ANGLE_ALL = "get/angle/+";
    /** 业务主题-全部振动消息 **/
    public static final String TOPIC_VIBRATION_ALL = "get/vibration/+";
    /** 业务主题-全部气象消息 **/
    public static final String TOPIC_WEATHER_ALL = "get/weather/+";
}
