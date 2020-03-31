package com.cit.its.shipping.front.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨贺龙
 * @name EquipStatus
 * @create 2020-01-08 15:03
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设备运行状态")
public class EquipStatus {
    @ApiModelProperty("设备ID")
    //设备ID
    private Long id;
    //设备名称
    @ApiModelProperty("设备名称")
    private String name;
    //空载时间
    @ApiModelProperty("空载时间")
    private Integer noLoadTime;
    //正常时间
    @ApiModelProperty("正常时间")
    private Integer generalTime;
    //超载时间
    @ApiModelProperty("超载时间")
    private Integer overLoadTime;
    //空载占比
    @ApiModelProperty("空载占比")
    private Float noLoadPer;
    //正常占比
    @ApiModelProperty("正常占比")
    private Float generalPer;
    //超载占比
    @ApiModelProperty("超载占比")
    private Float overLoadPer;
    //设备运行状态评分
    @ApiModelProperty("设备运行状态评分")
    private Float status;

}
