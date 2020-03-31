package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cit.its.shipping.front.entity.Trigger;
import org.springframework.stereotype.Repository;

/**
 * @author 杨贺龙
 * @name TriggerMapper
 * @create 2019-12-02 9:28
 * @description:
 */
@Repository
public interface TriggerMapper extends BaseMapper<Trigger> {
    int createTrigger(String trigger_name, String equip, String val_name, float le_value, float ge_value, String desc);

    int replaceTrigger(String trigger_name, String equip, String val_name, String relation, int value);

    int dropTrigger(String trigger_name);
}
