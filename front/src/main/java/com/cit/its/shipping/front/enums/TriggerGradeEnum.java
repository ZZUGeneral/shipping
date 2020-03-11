package com.cit.its.shipping.front.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author 杨贺龙
 * @name TriggerGradeEnum
 * @create 2019-12-02 8:39
 * @description: 告警等级枚举
 */
public enum TriggerGradeEnum implements IEnum<Integer> {
    GRADE_NONE(0, "未分类"),

    GRADE_ONE(1, "信息"),

    GRADE_TWO(2, "一般"),

    GRADE_THREE(3, "警告"),

    GRADE_FOUR(4, "严重"),

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
