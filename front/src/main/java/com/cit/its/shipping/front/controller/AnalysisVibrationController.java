package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.dto.VibrationAnalysisDto;
import com.cit.its.shipping.front.dto.VibrationStatisticsDto;
import com.cit.its.shipping.front.entity.Vibration;
import com.cit.its.shipping.front.service.VibrationAnalysisService;
import com.cit.its.shipping.front.service.VibrationService;
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
@Api(value = "振动分析Controller",tags = "振动分析接口")
@RestController
public class AnalysisVibrationController {

    @Autowired
    private VibrationService vibrationService;

    @Autowired
    private VibrationAnalysisService vibrationAnalysisService;

    @PostMapping("analysis/vibration")
    public Result analysisVibrationData(String firstYear, String firstMonth, String secondYear, String secondMonth) {

        Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
        Map<String, LocalDateTime> secondDateTimeMap = getQueryDateMap(secondYear, secondMonth);
        List<Vibration> firstVibrationList = vibrationService.vibrationHistory(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        List<Vibration> secondVibrationList = vibrationService.vibrationHistory(null, secondDateTimeMap.get("beginDateTime"), secondDateTimeMap.get("endDateTime"));
        Map<String, List<Vibration>> firstVibrationMap = firstVibrationList.stream().collect(Collectors.groupingBy(Vibration::getTopic));
        Map<String, List<Vibration>> secondVibrationMap = secondVibrationList.stream().collect(Collectors.groupingBy(Vibration::getTopic));
        VibrationStatisticsDto firstVibrationVDto = vibrationService.vVibrationStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        VibrationStatisticsDto secondVibrationVDto = vibrationService.vVibrationStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        VibrationStatisticsDto firstVibrationHDto = vibrationService.hVibrationStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        VibrationStatisticsDto secondVibrationHDto = vibrationService.hVibrationStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("first_vibration", firstVibrationMap);
        dataMap.put("second_vibration", secondVibrationMap);
        dataMap.put("first_sta_v", firstVibrationVDto);
        dataMap.put("second_sta_v", secondVibrationVDto);
        dataMap.put("first_sta_h", firstVibrationHDto);
        dataMap.put("second_sta_h", secondVibrationHDto);
        return Result.success(dataMap);
    }

    @PostMapping("analysis/pieChart/vibration")
    public Result analysisAnglePieChart(String leftInterval, String rightInterval, String firstYear, String firstMonth, String count, String dataItem) {

        List<List<Integer>> resultList = intervalSet(leftInterval, rightInterval, count);
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(count); i++){
            Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
            int intervalMin = resultList.get(i).get(0);
            int intervalMax = resultList.get(i).get(1);
            VibrationAnalysisDto vVibrationAnalysis = vibrationAnalysisService.vVibrationAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            VibrationAnalysisDto hVibrationAnalysis = vibrationAnalysisService.hVibrationAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            dataMap.put("vibration_v_" + i, vVibrationAnalysis);
            dataMap.put("vibration_h_" + i, hVibrationAnalysis);
            dataMap.put("vibration_interval" + i, intervalMin + "-" + intervalMax);
        }
        return Result.success(dataMap);
    }

}
