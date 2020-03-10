package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("msg_water_level")
public class WaterLevel {

    private String id;

    private Float value;

    private Long time;

    private String topic;

}
