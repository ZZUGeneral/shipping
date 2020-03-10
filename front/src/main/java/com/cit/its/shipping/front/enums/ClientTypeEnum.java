package com.cit.its.shipping.front.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * MQTT 连接终端类型
 */
@Getter
public enum ClientTypeEnum {

    CODE_CLIENT(-1, "代码连接"),
    SENSOR_WATER_LEVEL(1, "水位传感器"),
    SENSOR_TILT(2, "倾斜传感器"),
    SENSOR_ANGLE(3, "角度传感器"),
    SENSOR_VIBRATION(4, "振动传感器"),
    SENSOR_WEATHER(5, "气象传感器");


    @EnumValue
    private int code;
    private String desc;

    ClientTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ClientTypeEnum getByCode(Integer code) {
        for (ClientTypeEnum value : values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        return null;
    }
}
