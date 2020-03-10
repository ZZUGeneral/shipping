package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.WeatherRainfallMapper;
import com.cit.its.shipping.front.dao.WeatherVisibilityMapper;
import com.cit.its.shipping.front.dto.WeatherRainfallStatisticsDto;
import com.cit.its.shipping.front.dto.WeatherVisibilityStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherRainfall;
import com.cit.its.shipping.front.entity.WeatherVisibility;
import com.cit.its.shipping.front.service.WeatherRainfallService;
import com.cit.its.shipping.front.service.WeatherVisibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class WeatherVisibilityServiceImpl extends ServiceImpl<WeatherVisibilityMapper, WeatherVisibility> implements WeatherVisibilityService {

    @Autowired
    private WeatherVisibilityMapper weatherVisibilityMapper;


    @Override
    public List<WeatherVisibility> weatherVisibilityHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherVisibility> wrapper = queryCondition(topic, beginDateTime, endDateTime);
        wrapper.orderByAsc(WeatherVisibility::getTime);
        return list(wrapper);
    }

    @Override
    public WeatherVisibilityStatisticsDto weatherVisibilityStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        WeatherVisibilityStatisticsDto dto = weatherVisibilityMapper.weatherVisibilityStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }


    private LambdaQueryWrapper queryCondition(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherVisibility> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherVisibility::getTopic, topic);
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
