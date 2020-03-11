package com.cit.its.shipping.front.entity;

import lombok.Data;

/**
 * @author 杨贺龙
 * @name EquipStatus
 * @create 2020-01-08 15:03
 * @description:
 */
@Data
public class EquipStatus {
    //设备ID
    private Long id;
    //设备名称
    private String name;
    //空载时间
    private Integer noLoadTime;
    //正常时间
    private Integer generalTime;
    //超载时间
    private Integer overLoadTime;
    //空载占比
    private Float noLoadPer;
    //正常占比
    private Float generalPer;
    //超载占比
    private Float overLoadPer;
    //设备运行状态评分
    private Float status;

}
