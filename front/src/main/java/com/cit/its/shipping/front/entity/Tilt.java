package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *@Author: 黄贵生
 *@Description: 倾斜传感器实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg_tilt")
public class Tilt {

    private String id;

    private Long time;

    private Float val1X;

    private Float val1Y;

    private Float val2X;

    private Float val2Y;

    private String topic;

}
