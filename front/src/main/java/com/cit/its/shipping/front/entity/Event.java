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
import org.apache.poi.ss.formula.functions.T;

import java.sql.Timestamp;

/**
 * @author 杨贺龙
 * @name Event
 * @create 2019-12-02 10:28
 * @description: 触发告警的事件
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("触发告警的事件")
@TableName("msg_event")
public class Event {

    @ApiModelProperty("事件ID")
    @TableId(type = IdType.AUTO)
    private long eventId;

    @ApiModelProperty("触发器名称")
    @TableField("trigger_name")
    private int triggerName;

    @ApiModelProperty("事件等级")
    @TableField("grade")
    private int grade;

    @ApiModelProperty("事件发生时间")
    @TableField("create_time")
    private Timestamp creaeteTime;

    @ApiModelProperty("处理人编号")
    @TableField("deal_no")
    private long dealNo;

    @ApiModelProperty("事件描述")
    @TableField("event_desc")
    private String eventDesc;

}
