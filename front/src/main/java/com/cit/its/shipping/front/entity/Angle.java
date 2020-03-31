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
@TableName("msg_angle")
@ApiModel("角度数据")
public class Angle {

    @ApiModelProperty("ID，唯一主键")
    @TableId("id")
    private String id;

    @ApiModelProperty("时间")
    @TableField("time")
    private Long time;

    @ApiModelProperty("角度值")
    @TableField("value")
    private Float value;

    @ApiModelProperty("mqtt主题")
    @TableField("topic")
    private String topic;

}
