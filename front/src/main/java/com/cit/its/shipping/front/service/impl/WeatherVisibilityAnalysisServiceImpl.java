package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.WeatherVisibilityMapper;
import com.cit.its.shipping.front.dto.WeatherVisibilityAnalysisDto;
import com.cit.its.shipping.front.entity.WeatherVisibility;
import com.cit.its.shipping.front.service.WeatherVisibilityAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class WeatherVisibilityAnalysisServiceImpl extends ServiceImpl<WeatherVisibilityMapper, WeatherVisibility> implements WeatherVisibilityAnalysisService {

    @Autowired
    private WeatherVisibilityMapper weatherVisibilityMapper;

    @Override
    public WeatherVisibilityAnalysisDto weatherVisibilityAnalysis(String topic, int leftInterval, int RightInterval,LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        WeatherVisibilityAnalysisDto dto = weatherVisibilityMapper.weatherVisibilityAnalysis(wrapper);
        return dto;
    }


    private LambdaQueryWrapper queryCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherVisibility> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherVisibility::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(WeatherVisibility::getValue, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(WeatherVisibility::getValue, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(WeatherVisibility::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WeatherVisibility::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
