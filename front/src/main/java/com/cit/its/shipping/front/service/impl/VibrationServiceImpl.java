package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.VibrationMapper;
import com.cit.its.shipping.front.dto.VibrationStatisticsDto;
import com.cit.its.shipping.front.entity.Vibration;
import com.cit.its.shipping.front.service.VibrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class VibrationServiceImpl extends ServiceImpl<VibrationMapper, Vibration> implements VibrationService {

    @Autowired
    private VibrationMapper vibrationMapper;


    @Override
    public List<Vibration> vibrationHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Vibration> wrapper = queryCondition(topic, beginDateTime, endDateTime);
        wrapper.orderByAsc(Vibration::getTime);
        System.out.println(wrapper.toString());
        return list(wrapper);
    }

    @Override
    public VibrationStatisticsDto vVibrationStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        VibrationStatisticsDto dto = vibrationMapper.vVibrationStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public void insertVibration(String topic, String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);
        Vibration vibration = new Vibration();
        vibration.setTime(Long.parseLong(jsonObject.get("time").toString()));
        vibration.setTopic(topic);
        vibration.setValueH(Float.parseFloat(jsonObject.get("val_h").toString()));
        vibration.setValueV(Float.parseFloat(jsonObject.get("val_v").toString()));
        int id = this.vibrationMapper.insert(vibration);
    }

    @Override
    public VibrationStatisticsDto hVibrationStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        VibrationStatisticsDto dto = vibrationMapper.hVibrationStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }


    private LambdaQueryWrapper queryCondition(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Vibration> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Vibration::getTopic, topic);
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
