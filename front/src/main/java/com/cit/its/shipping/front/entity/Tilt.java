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

/**
 * @Author: 杨贺龙
 * @Description: 倾斜传感器实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("msg_tilt")
@ApiModel("倾斜数据")
public class Tilt {

    @ApiModelProperty("ID，倾斜传感器数据主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    @ApiModelProperty("记录数据时间")
    @TableField("time")
    private Long time;

    @ApiModelProperty("中轴线左侧x轴倾斜角度值")
    @TableField("value_1x")
    private Float val1X;

    @ApiModelProperty("中轴线左侧y轴倾斜角度值")
    @TableField("value_1y")
    private Float val1Y;

    @ApiModelProperty("中轴线右侧x轴倾斜角度值")
    @TableField("value_2x")
    private Float val2X;

    @ApiModelProperty("中轴线右侧y轴倾斜角度值")
    @TableField("value_2y")
    private Float val2Y;

    @ApiModelProperty("mqtt主题")
    @TableField("topic")
    private String topic;

}
