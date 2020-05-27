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
    /**
     * 功能描述: <br>
     * 创建触发器
     *
     * @Param: [trigger_name, equip, val_name, grade, le_value, ge_value, desc]
     * @Return: void
     * @Author: YHL
     * @Date: 2020/4/26 9:57
     */
    public void createTrigger(@Param("trigger_id") int trigger_id, @Param("equip") String equip, @Param("val_name") String val_name, @Param("grade") int grade, @Param("le_value") float le_value, @Param("ge_value") float ge_value, @Param("desc") String desc);

    public int replaceTrigger(@Param("trigger_id") int trigger_id, @Param("equip") String equip, @Param("val_name") String val_name, @Param("grade") int grade, @Param("le_value") float le_value, @Param("ge_value") float ge_value, @Param("desc") String desc);

    public int dropTrigger(@Param("trigger_namae") String trigger_name);

}
