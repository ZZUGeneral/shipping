package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.dto.WaterLevelAnalysisDto;
import com.cit.its.shipping.front.dto.WaterLevelStatisticsDto;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.WaterLevelAnalysisService;
import com.cit.its.shipping.front.service.WaterLevelService;
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
public class AnalysisWaterLevelController {

    @Autowired
    private WaterLevelService waterLevelService;
    @Autowired
    private WaterLevelAnalysisService waterLevelAnalysisService;

    @PostMapping("analysis/waterLevel")
    public Result historyWaterLevelData(String firstYear, String firstMonth, String secondYear, String secondMonth) {

        Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
        Map<String, LocalDateTime> secondDateTimeMap = getQueryDateMap(secondYear, secondMonth);
        List<WaterLevel> firstWaterLevelList = waterLevelService.waterLevelHistory(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        List<WaterLevel> secondWaterLevelList = waterLevelService.waterLevelHistory(null, secondDateTimeMap.get("beginDateTime"), secondDateTimeMap.get("endDateTime"));
        Map<String, List<WaterLevel>> firstWaterLevelMap = firstWaterLevelList.stream().collect(Collectors.groupingBy(WaterLevel::getTopic));
        Map<String, List<WaterLevel>> secondWaterLevelMap = secondWaterLevelList.stream().collect(Collectors.groupingBy(WaterLevel::getTopic));
        WaterLevelStatisticsDto firstWaterLevelDto = waterLevelService.waterLevelStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        WaterLevelStatisticsDto secondWaterLevelDto = waterLevelService.waterLevelStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("waterLevel1", firstWaterLevelMap);
        dataMap.put("waterLevel2", secondWaterLevelMap);
        dataMap.put("sta1", firstWaterLevelDto);
        dataMap.put("sta2", secondWaterLevelDto);
        return Result.success(dataMap);
    }

    @PostMapping("analysis/pieChart/waterLevel")
    public Result analysisWaterLevelPieChart(String leftInterval, String rightInterval, String firstYear, String firstMonth, String count) {

        List<List<Integer>> resultList = intervalSet(leftInterval, rightInterval, count);
        System.out.println(resultList.toString());
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(count); i++){
            Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
            int intervalMin = resultList.get(i).get(0);
            int intervalMax = resultList.get(i).get(1);
            WaterLevelAnalysisDto waterLevelDto = waterLevelAnalysisService.waterLevelAnalysis(null, intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            dataMap.put("waterLevel" + i, waterLevelDto);
        }
        return Result.success(dataMap);
    }
}
