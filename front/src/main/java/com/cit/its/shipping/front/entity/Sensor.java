package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("msg_sensor")
public class Sensor {

    private int id;
    //传感器位置
    private String depart;
    //传感器名称
    private String name;
    // 获取数据的topic
    private String topic;
    //数据对应名称
    private String val_name;
    //传感器空载值
    private float noLoadValue;
    //传感器超载值
    private float overLoadValue;

}
