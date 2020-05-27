package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.cit.its.shipping.front.enums.TriggerGradeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 杨贺龙
 * @name Trigger
 * @create 2019-12-02 8:36
 * @description: 告警
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("msg_trigger")
@ApiModel("触发器")
public class Trigger {
    //告警编号
    @ApiModelProperty("触发器ID")
    @TableId(type = IdType.AUTO)
    @TableField("trigger_id")
    private int triggerId;

    //告警名称
    @ApiModelProperty("触发器名称")
    @TableField("trigger_name")
    private String triggerName;

    //告警等级
    @ApiModelProperty("触发器等级")
    @TableField("grade")
    private Integer grade;

    //该告警的设备
    @ApiModelProperty("触发器对应的传感器名称")
    @TableField("equip")
    private String equip;

    //数据项
    @ApiModelProperty("触发器的数据项")
    @TableField("data")
    private String data;

    //阈值
    @ApiModelProperty("触发数据项较小值")
    @TableField("le_value")
    private int leValue;

    @ApiModelProperty("触发器数据项较大值")
    @TableField("ge_value")
    private int geValue;

    //创建时间
    @ApiModelProperty("触发器创建时间")
    @TableField("create_time")
    private Date createTime;

    //告警描述
    @TableField("trigger_desc")
    @ApiModelProperty("触发事件描述")
    private String triggerDesc;

}
