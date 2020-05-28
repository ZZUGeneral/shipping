package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.WeatherRainfallStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherRainfall;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherRainfallService extends IService<WeatherRainfall> {

    List<WeatherRainfall> weatherRainfallHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherRainfallStatisticsDto weatherRainfallStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    void insertWeatherRainfall(String topic, String jsonContent);


}
