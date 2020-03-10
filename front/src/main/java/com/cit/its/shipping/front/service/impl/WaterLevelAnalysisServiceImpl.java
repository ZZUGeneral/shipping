package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.WaterLevelMapper;
import com.cit.its.shipping.front.dto.WaterLevelAnalysisDto;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.WaterLevelAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class WaterLevelAnalysisServiceImpl extends ServiceImpl<WaterLevelMapper, WaterLevel> implements WaterLevelAnalysisService {

    @Autowired
    private WaterLevelMapper WaterLevelMapper;

    @Override
    public WaterLevelAnalysisDto waterLevelAnalysis(String topic, int leftInterval, int RightInterval,LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        WaterLevelAnalysisDto dto = WaterLevelMapper.waterLevelAnalysis(wrapper);
        return dto;
    }


    private LambdaQueryWrapper queryCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WaterLevel> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WaterLevel::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(WaterLevel::getValue, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(WaterLevel::getValue, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(WaterLevel::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WaterLevel::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
