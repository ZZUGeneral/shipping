package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.dto.WeatherGeneralAnalysisDto;
import com.cit.its.shipping.front.dto.WeatherGeneralStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherGeneral;
import com.cit.its.shipping.front.service.WeatherGeneralAnalysisService;
import com.cit.its.shipping.front.service.WeatherGeneralService;
import com.cit.its.shipping.front.vo.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cit.its.shipping.front.enums.IntervalUtil.intervalSet;
import static com.cit.its.shipping.front.enums.TimeUtil.getQueryDateMap;

@Api(value = "天气常规分析Controller",tags = "天气常规分析接口")
@RestController
public class AnalysisWeatherGeneralController {

    @Autowired
    private WeatherGeneralService weatherGeneralService;

    @Autowired
    private WeatherGeneralAnalysisService weatherGeneralAnalysisService;

    @PostMapping("analysis/weather_general")
    public Result analysisWeatherGeneralData(String firstYear, String firstMonth, String secondYear, String secondMonth) {

        Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
        Map<String, LocalDateTime> secondDateTimeMap = getQueryDateMap(secondYear, secondMonth);
        List<WeatherGeneral> firstWeatherGeneralList = weatherGeneralService.weatherGeneralHistory(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        List<WeatherGeneral> secondWeatherGeneralList = weatherGeneralService.weatherGeneralHistory(null, secondDateTimeMap.get("beginDateTime"), secondDateTimeMap.get("endDateTime"));
        Map<String, List<WeatherGeneral>> firstWeatherGeneralMap = firstWeatherGeneralList.stream().collect(Collectors.groupingBy(WeatherGeneral::getTopic));
        Map<String, List<WeatherGeneral>> secondWeatherGeneralMap = secondWeatherGeneralList.stream().collect(Collectors.groupingBy(WeatherGeneral::getTopic));
        WeatherGeneralStatisticsDto firstTemperatureDto = weatherGeneralService.temperatureStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto secondTemperatureDto = weatherGeneralService.temperatureStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto firstHumidityDto = weatherGeneralService.humidityStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto secondHumidityDto = weatherGeneralService.humidityStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto firstAirPressureDto = weatherGeneralService.airPressureStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto secondAirPressureDto = weatherGeneralService.airPressureStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto firstWindSpeedDto = weatherGeneralService.windSpeedStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto secondWindSpeedDto = weatherGeneralService.windSpeedStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto firstWindDirectionDto = weatherGeneralService.windDirectionStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherGeneralStatisticsDto secondWindDirectionDto = weatherGeneralService.windDirectionStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("first_weather_general", firstWeatherGeneralMap);
        dataMap.put("second_weather_general", secondWeatherGeneralMap);
        dataMap.put("first_sta_temperature", firstTemperatureDto);
        dataMap.put("second_sta_temperature", secondTemperatureDto);
        dataMap.put("first_sta_humidity", firstHumidityDto);
        dataMap.put("second_sta_humidity", secondHumidityDto);
        dataMap.put("first_sta_airPressure", firstAirPressureDto);
        dataMap.put("second_sta_airPressure", secondAirPressureDto);
        dataMap.put("first_sta_windSpeed", firstWindSpeedDto);
        dataMap.put("second_sta_windSpeed", secondWindSpeedDto);
        dataMap.put("first_sta_windDirection", firstWindDirectionDto);
        dataMap.put("second_sta_windDirection", secondWindDirectionDto);
        return Result.success(dataMap);
    }

    @PostMapping("analysis/pieChart/weatherGeneral")
    public Result analysisAnglePieChart(String leftInterval, String rightInterval, String firstYear, String firstMonth, String count, String dataItem) {

        List<List<Integer>> resultList = intervalSet(leftInterval, rightInterval, count);
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(count); i++){
            Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
            int intervalMin = resultList.get(i).get(0);
            int intervalMax = resultList.get(i).get(1);
            WeatherGeneralAnalysisDto temperatureAnalysis = weatherGeneralAnalysisService.temperatureAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            WeatherGeneralAnalysisDto humidityAnalysis = weatherGeneralAnalysisService.humidityAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            WeatherGeneralAnalysisDto airPressureAnalysis = weatherGeneralAnalysisService.airPressureAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            WeatherGeneralAnalysisDto windSpeedAnalysis = weatherGeneralAnalysisService.windSpeedAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            WeatherGeneralAnalysisDto windDirectionAnalysis = weatherGeneralAnalysisService.windDirectionAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            dataMap.put("temperature_" + i, temperatureAnalysis);
            dataMap.put("humidity_" + i, humidityAnalysis);
            dataMap.put("airPressure_" + i, airPressureAnalysis);
            dataMap.put("windSpeed_" + i, windSpeedAnalysis);
            dataMap.put("windDirection_" + i, windDirectionAnalysis);
            dataMap.put("WeatherGeneral_interval" + i, intervalMin + "-" + intervalMax);
        }
        return Result.success(dataMap);
    }
}
