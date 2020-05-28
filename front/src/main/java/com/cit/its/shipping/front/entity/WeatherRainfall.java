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
@TableName("msg_weather_rainfall")
@ApiModel("降雨量数据")
public class WeatherRainfall {

    @ApiModelProperty("数据项ID")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    @ApiModelProperty("降雨量")
    @TableField("value")
    private Float value;

    @ApiModelProperty("时间")
    @TableField("time")
    private Long time;

    @ApiModelProperty("mqtt 主题")
    @TableField("topic")
    private String topic;

}
