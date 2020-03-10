package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.VibrationMapper;
import com.cit.its.shipping.front.dto.VibrationAnalysisDto;
import com.cit.its.shipping.front.dto.VibrationStatisticsDto;
import com.cit.its.shipping.front.entity.Vibration;
import com.cit.its.shipping.front.service.VibrationAnalysisService;
import com.cit.its.shipping.front.service.VibrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class VibrationAnalysisServiceImpl extends ServiceImpl<VibrationMapper, Vibration> implements VibrationAnalysisService {

    @Autowired
    private VibrationMapper vibrationMapper;

    @Override
    public VibrationAnalysisDto vVibrationAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryValueVCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        VibrationAnalysisDto dto = vibrationMapper.vVibrationAnalysis(wrapper);
        return dto;
    }

    @Override
    public VibrationAnalysisDto hVibrationAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryValueHCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        VibrationAnalysisDto dto = vibrationMapper.hVibrationAnalysis(wrapper);
        return dto;
    }


    private LambdaQueryWrapper queryValueHCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Vibration> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Vibration::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(Vibration::getValueH, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(Vibration::getValueH, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(Vibration::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(Vibration::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }

    private LambdaQueryWrapper queryValueVCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Vibration> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Vibration::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(Vibration::getValueV, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(Vibration::getValueV, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(Vibration::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(Vibration::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
