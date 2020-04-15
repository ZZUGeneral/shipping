package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.entity.WeatherVisibility;
import com.cit.its.shipping.front.service.WeatherVisibilityService;
import com.cit.its.shipping.front.vo.Result;
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
 * @Author: 黄贵生
 * @Description: 气象传感器能见度数据Controller
 */
@RestController
@ApiIgnore
public class WeatherVisibilityController {

    @Autowired
    private WeatherVisibilityService weatherVisibilityService;

    @ApiOperation(value = "获取能见度历史数据", notes = "获取能见度历史数据")
    @PostMapping("history/weather_visibility")
    public Result historyWeatherVisibilityData(@RequestParam String topic, @RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<WeatherVisibility> weatherVisibilityList = weatherVisibilityService.weatherVisibilityHistory(topic, beginDateTime, endDateTime);
//        angleList = angleList.subList(0, 1000);
        if (weatherVisibilityList.isEmpty()) {
            return Result.fail(200,"没有查询到数据，请再次确认查询条件");
        }
        return Result.success(weatherVisibilityList);
    }

}
