package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.WeatherRainfallAnalysisDto;
import com.cit.its.shipping.front.entity.WeatherRainfall;

import java.time.LocalDateTime;

public interface WeatherRainfallAnalysisService extends IService<WeatherRainfall> {

    WeatherRainfallAnalysisDto weatherRainfallAnalysis(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

}
