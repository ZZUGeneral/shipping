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
@TableName("msg_weather_visibility")
@ApiModel("可视度数据")
public class WeatherVisibility {

    @ApiModelProperty("数据项ID")
    @TableId
    private String id;

    @ApiModelProperty("可视度")
    @TableField("value")
    private Float value;

    @ApiModelProperty("时间")
    @TableField("time")
    private Long time;

    @ApiModelProperty("mqtt 主题")
    @TableField("topic")
    private String topic;

}
