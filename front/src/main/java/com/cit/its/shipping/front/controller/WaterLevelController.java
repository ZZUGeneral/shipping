package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.dto.WaterLevelStatisticsDto;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.WaterLevelService;
import com.cit.its.shipping.front.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 黄贵生
 * @Description: 水位传感器Controller
 */
@RestController
//@ApiIgnore
public class WaterLevelController {

    @Autowired
    private WaterLevelService waterLevelService;

    @ApiOperation(value = "获取水位历史数据", notes = "获取水位历史数据")
    @PostMapping("history/waterLevel")
    public Result historyWaterLevelData(@RequestParam String topic, @RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<WaterLevel> waterLevelList = waterLevelService.waterLevelHistory(null, beginDateTime, endDateTime);
//        waterLevelList = waterLevelList.subList(0, 1000);
        Map<String, List<WaterLevel>> waterLevelMap = waterLevelList.stream().collect(Collectors.groupingBy(WaterLevel::getTopic));
        WaterLevelStatisticsDto waterLevelStatisticsDto = waterLevelService.waterLevelStatistics(null, beginDateTime, endDateTime);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("waterLevel", waterLevelMap);
        dataMap.put("sta", waterLevelStatisticsDto);
        if (waterLevelList.isEmpty()) {
            return Result.fail(200,"没有查询到数据，请再次确认查询条件");
        }
        return Result.success(waterLevelList);
    }
}
