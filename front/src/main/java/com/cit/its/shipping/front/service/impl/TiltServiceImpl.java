package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.TiltMapper;
import com.cit.its.shipping.front.dto.TiltStatisticsDto;
import com.cit.its.shipping.front.entity.Tilt;
import com.cit.its.shipping.front.service.TiltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.TileObserver;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class TiltServiceImpl extends ServiceImpl<TiltMapper, Tilt> implements TiltService {

    @Autowired
    private TiltMapper tiltMapper;


    @Override
    public List<Tilt> tiltHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Tilt> wrapper = queryCondition(topic, beginDateTime, endDateTime);
        wrapper.orderByAsc(Tilt::getTime);
        return list(wrapper);
    }

    @Override
    public TiltStatisticsDto x1TiltStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        TiltStatisticsDto dto = tiltMapper.x1TiltStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public TiltStatisticsDto y1TiltStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        TiltStatisticsDto dto = tiltMapper.y1TiltStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public TiltStatisticsDto x2TiltStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        TiltStatisticsDto dto = tiltMapper.x2TiltStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public TiltStatisticsDto y2TiltStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        TiltStatisticsDto dto = tiltMapper.y2TiltStatistics(wrapper);
        dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public void insertTilt(String topic, String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);
        Tilt tilt = new Tilt();
        tilt.setTopic(topic);
        tilt.setTime(Long.parseLong(jsonObject.get("time").toString()));
        tilt.setVal1X(Float.parseFloat(jsonObject.get("val_1x").toString()));
        tilt.setVal1Y(Float.parseFloat(jsonObject.get("val_1y").toString()));
        tilt.setVal2X(Float.parseFloat(jsonObject.get("val_2x").toString()));
        tilt.setVal2Y(Float.parseFloat(jsonObject.get("val_2y").toString()));
        int id = tiltMapper.insert(tilt);
    }


    private LambdaQueryWrapper queryCondition(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Tilt> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(Tilt::getTopic, topic);
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
