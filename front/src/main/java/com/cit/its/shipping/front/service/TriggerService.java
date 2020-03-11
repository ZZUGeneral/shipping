package com.cit.its.shipping.front.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.entity.Trigger;

/**
 * @author 杨贺龙
 * @name TriggerService
 * @create 2019-12-02 9:28
 * @description: 告警处理
 */
public interface TriggerService extends IService<Trigger> {
    //创建触发器
    int createTrigger(Trigger trigger);

    //更新触发器
    int replaceTrigger(Trigger trigger);

    //删除触发器
    int dropTrigger(int trigger_id);
}
