package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.cit.its.shipping.front.enums.TriggerGradeEnum;
import lombok.Data;

/**
 * @author 杨贺龙
 * @name Trigger
 * @create 2019-12-02 8:36
 * @description: 告警
 */
@Data
@TableName("cr_triggert")
public class Trigger {
    //告警编号
    @TableId(type = IdType.AUTO)
    private int id;
    //告警名称
    private String trigger_name;
    //告警等级
    private TriggerGradeEnum grade;
    //该告警的设备
    private String equip;
    //数据项
    private String data;
    //与阈值之间的管理,大于,等于,小于
    private String relation;
    //阈值
    private int value;
    //告警描述
    private String desc;

}
