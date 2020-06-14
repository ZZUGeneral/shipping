package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.dto.TiltAnalysisDto;
import com.cit.its.shipping.front.dto.TiltStatisticsDto;
import com.cit.its.shipping.front.entity.Tilt;
import com.cit.its.shipping.front.service.TiltAnalysisService;
import com.cit.its.shipping.front.service.TiltService;
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
@Api(value = "倾斜分析Controller",tags = "倾斜分析接口")
@RestController
public class AnalysisTiltController {

    @Autowired
    private TiltService tiltService;

    @Autowired
    private TiltAnalysisService tiltAnalysisService;

    @PostMapping("analysis/tilt")
    public Result analysisTiltData(String firstYear, String firstMonth, String secondYear, String secondMonth) {

        Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
        Map<String, LocalDateTime> secondDateTimeMap = getQueryDateMap(secondYear, secondMonth);
        List<Tilt> firstAngleList = tiltService.tiltHistory(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        List<Tilt> secondAngleList = tiltService.tiltHistory(null, secondDateTimeMap.get("beginDateTime"), secondDateTimeMap.get("endDateTime"));
        Map<String, List<Tilt>> firstAngleMap = firstAngleList.stream().collect(Collectors.groupingBy(Tilt::getTopic));
        Map<String, List<Tilt>> secondAngleMap = secondAngleList.stream().collect(Collectors.groupingBy(Tilt::getTopic));
        TiltStatisticsDto firstTiltX1Dto = tiltService.x1TiltStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        TiltStatisticsDto secondTiltX1Dto = tiltService.x1TiltStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        TiltStatisticsDto firstTiltX2Dto = tiltService.x2TiltStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        TiltStatisticsDto secondTiltX2Dto = tiltService.x2TiltStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        TiltStatisticsDto firstTiltY1Dto = tiltService.y1TiltStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        TiltStatisticsDto secondTilY11Dto = tiltService.y1TiltStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        TiltStatisticsDto firstTiltY2Dto = tiltService.y2TiltStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        TiltStatisticsDto secondTiltY2Dto = tiltService.y2TiltStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("first_tilt", firstAngleMap);
        dataMap.put("second_tilt", secondAngleMap);
        dataMap.put("first_sta_x1", firstTiltX1Dto);
        dataMap.put("second_sta_x1", secondTiltX1Dto);
        dataMap.put("first_sta_x2", firstTiltX2Dto);
        dataMap.put("second_sta_x2", secondTiltX2Dto);
        dataMap.put("first_sta_y1", firstTiltY1Dto);
        dataMap.put("second_sta_y1", secondTilY11Dto);
        dataMap.put("first_sta_y2", firstTiltY2Dto);
        dataMap.put("second_sta_y2", secondTiltY2Dto);
        return Result.success(dataMap);
    }

    @PostMapping("analysis/pieChart/tilt")
    public Result analysisAnglePieChart(String leftInterval, String rightInterval, String firstYear, String firstMonth, String count, String dataItem) {

        List<List<Integer>> resultList = intervalSet(leftInterval, rightInterval, count);
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(count); i++){
            Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
            int intervalMin = resultList.get(i).get(0);
            int intervalMax = resultList.get(i).get(1);
            TiltAnalysisDto x1TiltAnalysis = tiltAnalysisService.x1TiltAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            TiltAnalysisDto y1TiltAnalysis = tiltAnalysisService.y1TiltAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            TiltAnalysisDto x2TiltAnalysis = tiltAnalysisService.x2TiltAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            TiltAnalysisDto y2TiltAnalysis = tiltAnalysisService.y2TiltAnalysis(null, dataItem,intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            dataMap.put("tilt_x1_" + i, x1TiltAnalysis);
            dataMap.put("tilt_y1_" + i, y1TiltAnalysis);
            dataMap.put("tilt_x2_" + i, x2TiltAnalysis);
            dataMap.put("tilt_y2_" + i, y2TiltAnalysis);
            dataMap.put("tilt_interval" + i, intervalMin + "-" + intervalMax);
        }
        return Result.success(dataMap);
    }
}
