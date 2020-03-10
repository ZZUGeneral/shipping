package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.WeatherGeneralMapper;
import com.cit.its.shipping.front.dto.WeatherGeneralAnalysisDto;
import com.cit.its.shipping.front.dto.WeatherGeneralAnalysisDto;
import com.cit.its.shipping.front.entity.WeatherGeneral;
import com.cit.its.shipping.front.service.WeatherGeneralAnalysisService;
import com.cit.its.shipping.front.service.WeatherGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class WeatherGeneralAnalysisServiceImpl extends ServiceImpl<WeatherGeneralMapper, WeatherGeneral> implements WeatherGeneralAnalysisService {

    @Autowired
    private WeatherGeneralMapper weatherGeneralMapper;

    @Override
    public WeatherGeneralAnalysisDto temperatureAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryTemperatureCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        WeatherGeneralAnalysisDto dto = weatherGeneralMapper.temperatureAnalysis(wrapper);
        return dto;
    }

    @Override
    public WeatherGeneralAnalysisDto humidityAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryHumidityCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        WeatherGeneralAnalysisDto dto = weatherGeneralMapper.humidityAnalysis(wrapper);
        return dto;
    }

    @Override
    public WeatherGeneralAnalysisDto airPressureAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryAirPressureCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        WeatherGeneralAnalysisDto dto = weatherGeneralMapper.airPressureAnalysis(wrapper);
        return dto;
    }

    @Override
    public WeatherGeneralAnalysisDto windSpeedAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryWindSpeedCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        WeatherGeneralAnalysisDto dto = weatherGeneralMapper.windSpeedAnalysis(wrapper);
        return dto;
    }

    @Override
    public WeatherGeneralAnalysisDto windDirectionAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryWindDirectionCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        WeatherGeneralAnalysisDto dto = weatherGeneralMapper.windDirectionAnalysis(wrapper);
        return dto;
    }


    private LambdaQueryWrapper queryTemperatureCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherGeneral> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherGeneral::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(WeatherGeneral::getTemperature, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(WeatherGeneral::getTemperature, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(WeatherGeneral::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WeatherGeneral::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }

    private LambdaQueryWrapper queryHumidityCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherGeneral> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherGeneral::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(WeatherGeneral::getHumidity, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(WeatherGeneral::getHumidity, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(WeatherGeneral::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WeatherGeneral::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }

    private LambdaQueryWrapper queryAirPressureCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherGeneral> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherGeneral::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(WeatherGeneral::getAirPressure, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(WeatherGeneral::getAirPressure, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(WeatherGeneral::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WeatherGeneral::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }

    private LambdaQueryWrapper queryWindSpeedCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherGeneral> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherGeneral::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(WeatherGeneral::getWindSpeed, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(WeatherGeneral::getWindSpeed, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(WeatherGeneral::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WeatherGeneral::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }

    private LambdaQueryWrapper queryWindDirectionCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WeatherGeneral> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WeatherGeneral::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(WeatherGeneral::getWindDirection, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(WeatherGeneral::getWindDirection, RightInterval);
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
