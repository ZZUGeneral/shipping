package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.AngleMapper;
import com.cit.its.shipping.front.dto.AngleStatisticsDto;
import com.cit.its.shipping.front.entity.Angle;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.AngleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.event.AncestorListener;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class AngleServiceImpl extends ServiceImpl<AngleMapper, Angle> implements AngleService {

    @Autowired
    private AngleMapper angleMapper;


    @Override
    public List<Angle> angleHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Angle> wrapper = queryCondition(topic, beginDateTime, endDateTime);
        wrapper.orderByAsc(Angle::getTime);
        return list(wrapper);
    }

    @Override
    public AngleStatisticsDto angleStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        AngleStatisticsDto dto = angleMapper.angleStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public void insertAngle(String topic, String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);
        Angle angle = new Angle();
        angle.setValue(Float.parseFloat(jsonObject.get("val").toString()));
        angle.setTopic(topic);
        angle.setTime(Long.parseLong(jsonObject.get("time").toString()));
        int id = this.angleMapper.insert(angle);
    }


    private LambdaQueryWrapper queryCondition(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Angle> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Angle::getTopic, topic);
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
