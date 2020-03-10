package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg_vibration")
public class Vibration {

    private String id;

    private Float valueH;

    private Float valueV;

    private Long time;

    private String topic;

}
