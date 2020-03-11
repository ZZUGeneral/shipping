package com.cit.its.shipping.front.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cit.its.shipping.front.dao.TriggerMapper;
import com.cit.its.shipping.front.entity.Trigger;
import com.cit.its.shipping.front.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 杨贺龙
 * @name TriggerServiceImpl
 * @create 2019-12-02 9:30
 * @description:
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class TriggerServiceImpl extends ServiceImpl<TriggerMapper, Trigger> implements TriggerService {
    @Autowired
    TriggerMapper triggerMapper;

    @Override
    public int createTrigger(Trigger trigger) {
        return 0;
    }

    @Override
    public int replaceTrigger(Trigger trigger) {
        return 0;
    }

    @Override
    public int dropTrigger(int trigger_id) {
        return 0;
    }
}
