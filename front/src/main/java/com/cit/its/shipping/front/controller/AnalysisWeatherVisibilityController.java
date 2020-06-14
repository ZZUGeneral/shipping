package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.dto.WeatherVisibilityAnalysisDto;
import com.cit.its.shipping.front.dto.WeatherVisibilityStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherVisibility;
import com.cit.its.shipping.front.service.WeatherVisibilityAnalysisService;
import com.cit.its.shipping.front.service.WeatherVisibilityService;
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

@Api(value = "能见度分析Controller", tags = "能见度分析接口")
@RestController
public class AnalysisWeatherVisibilityController {

    @Autowired
    private WeatherVisibilityService weatherVisibilityService;

    @Autowired
    private WeatherVisibilityAnalysisService weatherVisibilityAnalysisService;

    @PostMapping("analysis/weatherVisibility")
    public Result analysisWeatherVisibilityData(String firstYear, String firstMonth, String secondYear, String secondMonth) {

        Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
        Map<String, LocalDateTime> secondDateTimeMap = getQueryDateMap(secondYear, secondMonth);
        List<WeatherVisibility> firstWeatherVisibilityList = weatherVisibilityService.weatherVisibilityHistory(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        List<WeatherVisibility> secondWeatherVisibilityList = weatherVisibilityService.weatherVisibilityHistory(null, secondDateTimeMap.get("beginDateTime"), secondDateTimeMap.get("endDateTime"));
        Map<String, List<WeatherVisibility>> firstWeatherVisibilityMap = firstWeatherVisibilityList.stream().collect(Collectors.groupingBy(WeatherVisibility::getTopic));
        Map<String, List<WeatherVisibility>> secondWeatherVisibilityMap = secondWeatherVisibilityList.stream().collect(Collectors.groupingBy(WeatherVisibility::getTopic));
        WeatherVisibilityStatisticsDto firstWeatherVisibilityDto = weatherVisibilityService.weatherVisibilityStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WeatherVisibilityStatisticsDto secondWeatherVisibilityDto = weatherVisibilityService.weatherVisibilityStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("first_weather_visibility", firstWeatherVisibilityMap);
        dataMap.put("second_weather_visibility", secondWeatherVisibilityMap);
        dataMap.put("first_sta", firstWeatherVisibilityDto);
        dataMap.put("second_sta", secondWeatherVisibilityDto);
        return Result.success(dataMap);
    }

    @PostMapping("analysis/pieChart/weather_visibility")
    public Result analysisWeatherVisibilityPieChart(String leftInterval, String rightInterval, String firstYear, String firstMonth, String count) {

        List<List<Integer>> resultList = intervalSet(leftInterval, rightInterval, count);
        System.out.println(resultList.toString());
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(count); i++) {
            Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
            int intervalMin = resultList.get(i).get(0);
            int intervalMax = resultList.get(i).get(1);
            WeatherVisibilityAnalysisDto weatherVisibilityDto = weatherVisibilityAnalysisService.weatherVisibilityAnalysis(null, intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            dataMap.put("weatherVisibility" + i, weatherVisibilityDto);
        }
        return Result.success(dataMap);
    }
}
