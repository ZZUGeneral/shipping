package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.TiltMapper;
import com.cit.its.shipping.front.dto.TiltAnalysisDto;
import com.cit.its.shipping.front.entity.Tilt;
import com.cit.its.shipping.front.service.TiltAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TiltAnalysisServiceImpl extends ServiceImpl<TiltMapper, Tilt> implements TiltAnalysisService {

    @Autowired
    private TiltMapper tiltMapper;

    @Override
    public TiltAnalysisDto x1TiltAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryVal1XCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        TiltAnalysisDto dto = tiltMapper.x1TiltAnalysis(wrapper);
        return dto;
    }

    @Override
    public TiltAnalysisDto y1TiltAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryVal1YCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        TiltAnalysisDto dto = tiltMapper.y1TiltAnalysis(wrapper);
        return dto;
    }

    @Override
    public TiltAnalysisDto x2TiltAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryVal2XCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        TiltAnalysisDto dto = tiltMapper.x2TiltAnalysis(wrapper);
        return dto;
    }

    @Override
    public TiltAnalysisDto y2TiltAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryVal2YCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        TiltAnalysisDto dto = tiltMapper.y2TiltAnalysis(wrapper);
        return dto;
    }

    private LambdaQueryWrapper queryVal1XCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Tilt> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Tilt::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(Tilt::getVal1X, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(Tilt::getVal1X, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(Tilt::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(Tilt::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }

    private LambdaQueryWrapper queryVal1YCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Tilt> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Tilt::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(Tilt::getVal1Y, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(Tilt::getVal1Y, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(Tilt::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(Tilt::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }

    private LambdaQueryWrapper queryVal2XCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Tilt> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Tilt::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(Tilt::getVal2X, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(Tilt::getVal2X, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(Tilt::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(Tilt::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }

    private LambdaQueryWrapper queryVal2YCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Tilt> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Tilt::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(Tilt::getVal2Y, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(Tilt::getVal2Y, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(Tilt::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(Tilt::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
