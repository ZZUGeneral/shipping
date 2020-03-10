package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.WaterLevelAnalysisDto;
import com.cit.its.shipping.front.entity.WaterLevel;

import java.time.LocalDateTime;

public interface WaterLevelAnalysisService extends IService<WaterLevel> {

    WaterLevelAnalysisDto waterLevelAnalysis(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

}
