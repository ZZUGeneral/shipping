package com.cit.its.shipping.front.entity;

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
@TableName("msg_vibration")
@ApiModel("振动数据")
public class Vibration {

    @ApiModelProperty("振动数据项ID")
    @TableId
    private String id;

    @ApiModelProperty("水平方向振动频率")
    @TableField("value_h")
    private Float valueH;

    @ApiModelProperty("垂直方向振动频率")
    @TableField("value_v")
    private Float valueV;

    @ApiModelProperty("时间")
    @TableField("time")
    private Long time;

    @ApiModelProperty("mqtt 主题")
    @TableField("topic")
    private String topic;

}
