package com.cit.its.shipping.front.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

/**
 * @author 杨贺龙
 * @name TriggerGradeEnum
 * @create 2019-12-02 8:39
 * @description: 告警等级枚举
 */
@ApiModel(value = "告警等级枚举")
public enum TriggerGradeEnum implements IEnum<Integer> {
    @ApiModelProperty("未分类告警")
    GRADE_NONE(0, "未分类"),
    @ApiModelProperty("信息告警")
    GRADE_ONE(1, "信息"),
    @ApiModelProperty("一般告警")
    GRADE_TWO(2, "一般"),
    @ApiModelProperty("警告告警")
    GRADE_THREE(3, "警告"),
    @ApiModelProperty("严重告警")
    GRADE_FOUR(4, "严重"),
    @ApiModelProperty("灾难告警")
    GRADE_FIVE(5, "灾难");


    private int value;
    private String desc;

    TriggerGradeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.desc;
    }

}
