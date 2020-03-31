package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg_sensor")
@ApiModel("传感器设备")
public class Sensor {

    @ApiModelProperty("传感器设备ID")
    @TableId
    private int id;

    //传感器位置
    @ApiModelProperty("传感器位置")
    @TableField("depart")
    private String depart;
    //传感器名称
    @ApiModelProperty("传感器名称")
    @TableField("name")
    private String name;
    // 获取数据的topic
    @ApiModelProperty("传感器对应的主题")
    @TableField("topic")
    private String topic;
    //数据对应名称
    @ApiModelProperty("传感器对应的数据项")
    private String val_name;

}
