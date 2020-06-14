package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.WaterLevelMapper;
import com.cit.its.shipping.front.dto.WaterLevelStatisticsDto;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.WaterLevelService;
import com.cit.its.shipping.front.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class WaterLevelServiceImpl extends ServiceImpl<WaterLevelMapper, WaterLevel> implements WaterLevelService {

    @Autowired
    private WaterLevelMapper waterLevelMapper;


    @Override
    public List<WaterLevel> waterLevelHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WaterLevel> wrapper = queryCondition(topic, beginDateTime, endDateTime);
        wrapper.orderByAsc(WaterLevel::getTime);
        return list(wrapper);
    }

    @Override
    public WaterLevelStatisticsDto waterLevelStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        System.out.println("===========================" + topic);
        LambdaQueryWrapper wrapper = queryCondition(topic, beginDateTime, endDateTime);
        WaterLevelStatisticsDto dto = waterLevelMapper.waterLevelStatistics(wrapper);
        // dto.setTopic(topic);
        dto.setBeginDateTime(beginDateTime);
        dto.setEndDateTime(endDateTime);
        return dto;
    }

    @Override
    public void insertWaterLevel(String topic, String jsonContent) {
        JSONObject jsonObject = new JSONObject(jsonContent);
        WaterLevel waterLevel = new WaterLevel();
        waterLevel.setValue(Float.parseFloat(jsonObject.get("val").toString()));
        waterLevel.setTopic(topic);
        waterLevel.setTime(Long.parseLong(jsonObject.get("time").toString()));
        int id = this.waterLevelMapper.insert(waterLevel);
    }


    private LambdaQueryWrapper queryCondition(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<WaterLevel> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(topic)) {
            wrapper.eq(WaterLevel::getTopic, topic);
        }
        if (beginDateTime != null) {
            wrapper.ge(WaterLevel::getTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(WaterLevel::getTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
