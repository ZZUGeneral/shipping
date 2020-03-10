package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.WeatherRainfallMapper;
import com.cit.its.shipping.front.dto.WeatherRainfallAnalysisDto;
import com.cit.its.shipping.front.entity.WeatherRainfall;
import com.cit.its.shipping.front.service.WeatherRainfallAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class WeatherRainfallAnalysisServiceImpl extends ServiceImpl<WeatherRainfallMapper, WeatherRainfall> implements WeatherRainfallAnalysisService {

    @Autowired
    private WeatherRainfallMapper weatherRainfallMapper;

    @Override
    public WeatherRainfallAnalysisDto weatherRainfallAnalysis(String topic, int leftInterval, int RightInterval,LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        WeatherRainfallAnalysisDto dto = weatherRainfallMapper.weatherRainfallAnalysis(wrapper);
        return dto;
    }


    private LambdaQueryWrapper queryCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherRainfall> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherRainfall::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(WeatherRainfall::getValue, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(WeatherRainfall::getValue, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(WeatherRainfall::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WeatherRainfall::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
