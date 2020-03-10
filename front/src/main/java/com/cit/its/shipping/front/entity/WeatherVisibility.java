package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg_weather_visibility")
public class WeatherVisibility {

    private String id;

    private Float value;

    private Long time;

    private String topic;

}
