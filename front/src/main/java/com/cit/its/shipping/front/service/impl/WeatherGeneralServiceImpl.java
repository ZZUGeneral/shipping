package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.WaterLevelMapper;
import com.cit.its.shipping.front.dao.WeatherGeneralMapper;
import com.cit.its.shipping.front.dto.WaterLevelStatisticsDto;
import com.cit.its.shipping.front.dto.WeatherGeneralStatisticsDto;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.entity.WeatherGeneral;
import com.cit.its.shipping.front.service.WaterLevelService;
import com.cit.its.shipping.front.service.WeatherGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class WeatherGeneralServiceImpl extends ServiceImpl<WeatherGeneralMapper, WeatherGeneral> implements WeatherGeneralService {

    @Autowired
    private WeatherGeneralMapper weatherGeneralMapper;


    @Override
    public List<WeatherGeneral> weatherGeneralHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherGeneral> wrapper = queryCondition(topic, beginDateTime, endDateTime);
        wrapper.orderByAsc(WeatherGeneral::getTime);
        return list(wrapper);
    }

    @Override
    public WeatherGeneralStatisticsDto temperatureStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto dto = weatherGeneralMapper.temperatureStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public WeatherGeneralStatisticsDto humidityStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto dto = weatherGeneralMapper.humidityStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public WeatherGeneralStatisticsDto airPressureStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto dto = weatherGeneralMapper.airPressureStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public WeatherGeneralStatisticsDto windSpeedStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto dto = weatherGeneralMapper.windSpeedStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public WeatherGeneralStatisticsDto windDirectionStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto dto = weatherGeneralMapper.windDirectionStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }


    private LambdaQueryWrapper queryCondition(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherGeneral> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherGeneral::getTopic, topic);
        }
        if (beginDateTime != null) {
            wrapper.ge(WeatherGeneral::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WeatherGeneral::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
