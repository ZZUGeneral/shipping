package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.WeatherVisibilityAnalysisDto;
import com.cit.its.shipping.front.entity.WeatherVisibility;

import java.time.LocalDateTime;

public interface WeatherVisibilityAnalysisService extends IService<WeatherVisibility> {

    WeatherVisibilityAnalysisDto weatherVisibilityAnalysis(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

}
