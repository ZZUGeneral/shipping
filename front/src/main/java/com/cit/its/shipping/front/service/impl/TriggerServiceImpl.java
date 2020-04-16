package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.cit.its.shipping.front.dao.TriggerMapper;
import com.cit.its.shipping.front.entity.Trigger;
import com.cit.its.shipping.front.service.TriggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        if (ObjectUtil.isNull(trigger)) {
            return 0;
        }
        try {
            triggerMapper.createTrigger(trigger.getTriggerName(), trigger.getEquip(), trigger.getData(), trigger.getGrade().getValue(), trigger.getLeValue(), trigger.getGeValue(), trigger.getDesc());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        int rs = triggerMapper.insert(trigger);
        return rs;
    }

    @Override
    public int replaceTrigger(Trigger trigger) {
        return 0;
    }

    @Override
    public int dropTrigger(String triggerName) {
        try {
            triggerMapper.dropTrigger(triggerName);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        QueryWrapper<Trigger> wrapper = new QueryWrapper<>();
        wrapper.eq("triggerName", triggerName);
        int rs = triggerMapper.delete(wrapper);
        return rs;
    }

    @Override
    public int selectTrigger(String triggerName) {
        QueryWrapper<Trigger> wrapper = new QueryWrapper<>();
        wrapper.eq("triggerName", triggerName);
        Trigger trigger = triggerMapper.selectOne(wrapper);
        if(ObjectUtil.isNull(trigger)){
            return 0;
        }
        return 1;
    }

    @Override
    public IPage<Trigger> triggerPageData(Trigger trigger, int currentPage, int size) {
        Page page = new Page(currentPage, size);
        QueryWrapper<Trigger> queryWrapper = new QueryWrapper<>();
        if (StrUtil.isNotEmpty(trigger.getTriggerName())) {
            queryWrapper.like("trigger_name", trigger.getTriggerName());
        }
        if (ObjectUtil.isNotNull(trigger.getGrade())) {
            queryWrapper.eq("grade", trigger.getGrade());
        }
        if (StrUtil.isNotEmpty(trigger.getEquip())) {
            queryWrapper.like("equip", trigger.getEquip());
        }
        if (StrUtil.isNotEmpty(trigger.getData())) {
            queryWrapper.like("data", trigger.getData());
        }
        queryWrapper.orderByAsc("createTime", "grade", "trigger_name");
        IPage<Trigger> iPage = triggerMapper.selectPage(page, queryWrapper);
        return iPage;
    }
}
