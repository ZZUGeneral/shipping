package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 杨贺龙
 * @name Event
 * @create 2019-12-02 10:28
 * @description: 触发告警的事件
 */

@Data
public class Event {
    @TableId(type = IdType.AUTO)
    private long eventId;

    private int triggerNamw;

    private int grade;

    private Timestamp creaeteTime;

    private long dealNo;

    private String eventDesc;

}
