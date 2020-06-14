package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.entity.WeatherGeneral;
import com.cit.its.shipping.front.service.WeatherGeneralService;
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
 * @Description: 气象传感器常规数据Controller
 */
@RestController
@Api(value = "气象常规Controller", tags = "气象常规接口")
public class WeatherGeneralController {

    @Autowired
    private WeatherGeneralService weatherGeneralService;

    @ApiOperation(value = "获取气象常规历史数据", notes = "获取气象常规历史数据")
    @PostMapping("history/weather_general")
    public Result historyWeatherGeneralData(@RequestParam String topic, @RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<WeatherGeneral> weatherGeneralList = weatherGeneralService.weatherGeneralHistory(topic, beginDateTime, endDateTime);
//        angleList = angleList.subList(0, 1000);
        /*Map<String, List<WeatherGeneral>> weatherGeneralMap = weatherGeneralList.stream().collect(Collectors.groupingBy(WeatherGeneral::getTopic));
        WeatherGeneralStatisticsDto temperatureStatisticsDto = weatherGeneralService.temperatureStatistics(null, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto humidityStatisticsDto = weatherGeneralService.humidityStatistics(null, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto airPressureStatisticsDto = weatherGeneralService.airPressureStatistics(null, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto windSpeedStatisticsDto = weatherGeneralService.windSpeedStatistics(null, beginDateTime, endDateTime);
        WeatherGeneralStatisticsDto windDirectionStatisticsDto = weatherGeneralService.windDirectionStatistics(null, beginDateTime, endDateTime);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("weatherGeneral", weatherGeneralMap);
        dataMap.put("sta_temperature", temperatureStatisticsDto);
        dataMap.put("sta_humidity", humidityStatisticsDto);
        dataMap.put("sta_airPressure", airPressureStatisticsDto);
        dataMap.put("sta_windSpeed", windSpeedStatisticsDto);
        dataMap.put("sta_windDirection", windDirectionStatisticsDto);*/
        if (weatherGeneralList.isEmpty()) {
            return Result.fail(200,"没有查询到数据，请再次确认查询条件");
        }
//        for (WeatherGeneral general : weatherGeneralList) {
//            general.setDesc(general.getWindSpeed(), general.getWindDirection());
//        }
        return Result.success(weatherGeneralList);
    }
}
