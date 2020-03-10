package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.AngleMapper;
import com.cit.its.shipping.front.dto.AngleAnalysisDto;
import com.cit.its.shipping.front.dto.AngleStatisticsDto;
import com.cit.its.shipping.front.entity.Angle;
import com.cit.its.shipping.front.service.AngleAnalysisService;
import com.cit.its.shipping.front.service.AngleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class AnalysisAngleServiceImpl extends ServiceImpl<AngleMapper, Angle> implements AngleAnalysisService {

    @Autowired
    private AngleMapper angleMapper;

    @Override
    public AngleAnalysisDto angleAnalysisDto(String topic, int leftInterval, int RightInterval,LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, leftInterval, RightInterval, beginDateTime, endDateTime);
        AngleAnalysisDto dto = angleMapper.angleAnalysis(wrapper);
        return dto;
    }


    private LambdaQueryWrapper queryCondition(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Angle> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Angle::getTopic, topic);
        }
        if (leftInterval != 0) {
            wrapper.ge(Angle::getValue, leftInterval);
        }
        if (RightInterval != 0) {
            wrapper.le(Angle::getValue, RightInterval);
        }
        if (beginDateTime != null) {
            wrapper.ge(Angle::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(Angle::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
