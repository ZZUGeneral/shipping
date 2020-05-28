package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.WeatherGeneralStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherGeneral;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherGeneralService extends IService<WeatherGeneral> {

    List<WeatherGeneral> weatherGeneralHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralStatisticsDto temperatureStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralStatisticsDto humidityStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralStatisticsDto airPressureStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralStatisticsDto windSpeedStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralStatisticsDto windDirectionStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    void insertWeatherGeneral(String topic, String jsonContent);
}
