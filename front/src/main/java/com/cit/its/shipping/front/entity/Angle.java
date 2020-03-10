package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg_angle")
public class Angle {

    private String id;

    private Long time;

    private Float value;

    private String topic;

}
