package com.cit.its.shipping.front.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

//详细数据
@Data
@NoArgsConstructor
@ApiModel("详细数据")
public class EquipDetailData {

    @ApiModelProperty("时间")
    private Long time;
    
    @ApiModelProperty("数值")
    private Float value;
}
