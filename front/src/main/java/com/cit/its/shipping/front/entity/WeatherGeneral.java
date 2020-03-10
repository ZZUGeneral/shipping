package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg_weather_general")
public class WeatherGeneral {

    private String id;

    private Long time;

    private Float temperature;

    private Float humidity;

    private Float airPressure;

    private Float windSpeed;

    private Float windDirection;

    private String topic;

}
