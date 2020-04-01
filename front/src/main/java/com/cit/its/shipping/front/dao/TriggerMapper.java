package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cit.its.shipping.front.entity.Trigger;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 杨贺龙
 * @name TriggerMapper
 * @create 2019-12-02 9:28
 * @description:
 */
@Repository
public interface TriggerMapper extends BaseMapper<Trigger> {
    int createTrigger(@Param("trigger_name") String trigger_name, @Param("equip") String equip, @Param("val_name") String val_name, @Param("le_value") float le_value, @Param("ge_value") float ge_value, @Param("desc") String desc);

    int replaceTrigger(String trigger_name, String equip, String val_name, String relation, int value);

    int dropTrigger(@Param("trigger_namae") String trigger_name);
}
