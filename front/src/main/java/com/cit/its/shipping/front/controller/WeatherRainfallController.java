package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.entity.WeatherRainfall;
import com.cit.its.shipping.front.service.WeatherRainfallService;
import com.cit.its.shipping.front.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @Author: 杨贺龙
 * @Description: 气象传感器雨量数据Controller
 */
@RestController
@Api(value = "雨量Controller", tags = "雨量接口")
public class WeatherRainfallController {

    @Autowired
    private WeatherRainfallService weatherRainfallService;

    @ApiOperation(value = "获取雨量历史数据", notes = "获取雨量历史数据")
    @PostMapping("history/weather_rainfall")
    public Result historyWeatherRainfallData(@RequestParam String topic, @RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<WeatherRainfall> weatherRainfallList = weatherRainfallService.weatherRainfallHistory(null, beginDateTime, endDateTime);
//        angleList = angleList.subList(0, 1000);
        /*Map<String, List<WeatherRainfall>> weatherRainfallMap = weatherRainfallList.stream().collect(Collectors.groupingBy(WeatherRainfall::getTopic));
        WeatherRainfallStatisticsDto weatherRainfallStatisticsDto = weatherRainfallService.weatherRainfallStatistics(null, beginDateTime, endDateTime);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("weatherRainfall", weatherRainfallMap);
        dataMap.put("sta", weatherRainfallStatisticsDto);*/

        if (weatherRainfallList.isEmpty()) {
            return Result.fail(200,"没有查询到数据，请再次确认查询条件");
        }
        return Result.success(weatherRainfallList);
    }
}
