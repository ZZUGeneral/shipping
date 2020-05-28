package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.WeatherVisibilityStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherVisibility;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherVisibilityService extends IService<WeatherVisibility> {

    List<WeatherVisibility> weatherVisibilityHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherVisibilityStatisticsDto weatherVisibilityStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    void insertWeatherVisibility(String topic, String jsonContent);
}
