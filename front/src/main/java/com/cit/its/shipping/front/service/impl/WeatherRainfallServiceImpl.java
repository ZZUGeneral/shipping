package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.WeatherRainfallMapper;
import com.cit.its.shipping.front.dto.WeatherRainfallStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherRainfall;
import com.cit.its.shipping.front.service.WeatherRainfallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class WeatherRainfallServiceImpl extends ServiceImpl<WeatherRainfallMapper, WeatherRainfall> implements WeatherRainfallService {

    @Autowired
    private WeatherRainfallMapper weatherRainfallMapper;


    @Override
    public List<WeatherRainfall> weatherRainfallHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherRainfall> wrapper = queryCondition(topic, beginDateTime, endDateTime);
        wrapper.orderByAsc(WeatherRainfall::getTime);
        return list(wrapper);
    }

    @Override
    public WeatherRainfallStatisticsDto weatherRainfallStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        WeatherRainfallStatisticsDto dto = weatherRainfallMapper.weatherRainfallStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }


    private LambdaQueryWrapper queryCondition(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherRainfall> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherRainfall::getTopic, topic);
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
