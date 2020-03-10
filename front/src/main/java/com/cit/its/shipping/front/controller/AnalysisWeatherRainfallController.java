package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.dto.AngleStatisticsDto;
import com.cit.its.shipping.front.dto.WeatherRainfallAnalysisDto;
import com.cit.its.shipping.front.dto.WeatherRainfallStatisticsDto;
import com.cit.its.shipping.front.entity.Angle;
import com.cit.its.shipping.front.entity.WeatherRainfall;
import com.cit.its.shipping.front.service.AngleService;
import com.cit.its.shipping.front.service.WeatherRainfallAnalysisService;
import com.cit.its.shipping.front.service.WeatherRainfallService;
import com.cit.its.shipping.front.vo.Result;
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


@RestController
public class AnalysisWeatherRainfallController {

    @Autowired
    private WeatherRainfallService weatherRainfallService;

    @Autowired
    private WeatherRainfallAnalysisService weatherRainfallAnalysisService;

    @PostMapping("analysis/weather_rainfall")
    public Result analysisWeatherRainfallData(String firstYear, String firstMonth, String secondYear, String secondMonth) {

        Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
        Map<String, LocalDateTime> secondDateTimeMap = getQueryDateMap(secondYear, secondMonth);
        List<WeatherRainfall> firstWeatherRainfallList = weatherRainfallService.weatherRainfallHistory(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        List<WeatherRainfall> secondWeatherRainfallList = weatherRainfallService.weatherRainfallHistory(null, secondDateTimeMap.get("beginDateTime"), secondDateTimeMap.get("endDateTime"));
        Map<String, List<WeatherRainfall>> firstWeatherRainfallMap = firstWeatherRainfallList.stream().collect(Collectors.groupingBy(WeatherRainfall::getTopic));
        Map<String, List<WeatherRainfall>> secondWeatherRainfallMap = secondWeatherRainfallList.stream().collect(Collectors.groupingBy(WeatherRainfall::getTopic));
        WeatherRainfallStatisticsDto firstWeatherRainfallDto = weatherRainfallService.weatherRainfallStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherRainfallStatisticsDto secondWeatherRainfallDto = weatherRainfallService.weatherRainfallStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("first_weather_rainfall", firstWeatherRainfallMap);
        dataMap.put("second_weather_rainfall", secondWeatherRainfallMap);
        dataMap.put("first_sta", firstWeatherRainfallDto);
        dataMap.put("second_sta", secondWeatherRainfallDto);
        return Result.success(dataMap);
    }

    @PostMapping("analysis/pieChart/weatherRainfall")
    public Result analysisWeatherRainfallPieChart(String leftInterval, String rightInterval, String firstYear, String firstMonth, String count) {

        List<List<Integer>> resultList = intervalSet(leftInterval, rightInterval, count);
        System.out.println(resultList.toString());
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(count); i++){
            Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
            int intervalMin = resultList.get(i).get(0);
            int intervalMax = resultList.get(i).get(1);
            WeatherRainfallAnalysisDto weatherRainfallDto = weatherRainfallAnalysisService.weatherRainfallAnalysis(null, intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            dataMap.put("weatherRainfall" + i, weatherRainfallDto);
        }
        return Result.success(dataMap);
    }
}
