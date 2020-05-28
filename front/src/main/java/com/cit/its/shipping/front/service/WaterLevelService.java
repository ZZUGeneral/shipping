package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.WaterLevelStatisticsDto;
import com.cit.its.shipping.front.entity.WaterLevel;

import java.time.LocalDateTime;
import java.util.List;

public interface WaterLevelService extends IService<WaterLevel> {

    List<WaterLevel> waterLevelHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WaterLevelStatisticsDto waterLevelStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    void insertWaterLevel(String topic, String jsonContent);

}
