package com.cit.its.shipping.front.vo;

import com.cit.its.shipping.front.enums.TriggerGradeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 杨贺龙
 * @name EventDetailVO
 * @create 2019-12-02 14:30
 * @description:
 */
@Data
@ApiModel(value = "EventDetail对象", description = "触发事件的详细描述")
public class EventDetailVO {
    //触发事件编号
    @ApiModelProperty(value = "触发事件编号")
    private long event_id;
    //触发时间
    @ApiModelProperty(value = "事件触发时间")
    private Timestamp createTime;
    //事件名称
    @ApiModelProperty(value = "事件名称")
    private String event_name;
    //事件等级
    @ApiModelProperty(value = "事件等级")
    private TriggerGradeEnum grade;
    //触发事件的设备
    @ApiModelProperty(value = "触发事件的设备")
    private String equip;
    //触发事件的数据项
    @ApiModelProperty(value = "触发事件的数据项")
    private String data;
    //触发事件的描述
    @ApiModelProperty(value = "触发事件的描述")
    private String desc;
    //处理人的名称
    @ApiModelProperty(value = "处理人的名称")
    private String deal_no;
    //是否处理
    @ApiModelProperty(value = "是否处理")
    private String is_deal;


}
