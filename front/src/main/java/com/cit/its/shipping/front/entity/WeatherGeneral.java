package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg_weather_general")
@ApiModel("气象数据")
public class WeatherGeneral {

    @ApiModelProperty("数据项ID")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    @ApiModelProperty("时间")
    @TableField("time")
    private Long time;

    @ApiModelProperty("温度")
    @TableField("temperature")
    private Float temperature;

    @ApiModelProperty("湿度")
    @TableField("humidity")
    private Float humidity;

    @ApiModelProperty("气压")
    @TableField("airPressure")
    private Float airPressure;

    @ApiModelProperty("风速")
    @TableField("windSpeed")
    private Float windSpeed;

    @ApiModelProperty("风向")
    @TableField("windDirection")
    private Float windDirection;

    @ApiModelProperty("mqtt 主题")
    @TableField("topic")
    private String topic;

}
