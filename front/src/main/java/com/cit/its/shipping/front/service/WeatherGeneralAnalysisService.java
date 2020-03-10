package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.WeatherGeneralAnalysisDto;
import com.cit.its.shipping.front.entity.WeatherGeneral;

import java.time.LocalDateTime;
import java.util.List;

public interface WeatherGeneralAnalysisService extends IService<WeatherGeneral> {

    WeatherGeneralAnalysisDto temperatureAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralAnalysisDto humidityAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralAnalysisDto airPressureAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralAnalysisDto windSpeedAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    WeatherGeneralAnalysisDto windDirectionAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

}
