package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.dto.AngleAnalysisDto;
import com.cit.its.shipping.front.dto.AngleStatisticsDto;
import com.cit.its.shipping.front.entity.Angle;
import com.cit.its.shipping.front.service.AngleAnalysisService;
import com.cit.its.shipping.front.service.AngleService;
import com.cit.its.shipping.front.vo.Result;
import io.swagger.annotations.ApiOperation;
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
public class AnalysisAngleController {

    @Autowired
    private AngleService angleService;
    @Autowired
    private AngleAnalysisService angleAnalysisService;

    @PostMapping("analysis/angle")
    public Result analysisAngleData(String firstYear, String firstMonth, String secondYear, String secondMonth) {

        Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
        Map<String, LocalDateTime> secondDateTimeMap = getQueryDateMap(secondYear, secondMonth);
        List<Angle> firstAngleList = angleService.angleHistory(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        List<Angle> secondAngleList = angleService.angleHistory(null, secondDateTimeMap.get("beginDateTime"), secondDateTimeMap.get("endDateTime"));
        Map<String, List<Angle>> firstAngleMap = firstAngleList.stream().collect(Collectors.groupingBy(Angle::getTopic));
        Map<String, List<Angle>> secondAngleMap = secondAngleList.stream().collect(Collectors.groupingBy(Angle::getTopic));
        AngleStatisticsDto firstAngleDto = angleService.angleStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        AngleStatisticsDto secondAngleDto = angleService.angleStatistics(null, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("angle1", firstAngleMap);
        dataMap.put("angle2", secondAngleMap);
        dataMap.put("sta1", firstAngleDto);
        dataMap.put("sta2", secondAngleDto);
        return Result.success(dataMap);
    }

    @ApiOperation(value = "获取用户列表", notes = "获取所有用户的列表")
    @PostMapping("analysis/pieChart/angle")
    public Result analysisAnglePieChart(String leftInterval, String rightInterval, String firstYear, String firstMonth, String count) {

        List<List<Integer>> resultList = intervalSet(leftInterval, rightInterval, count);
        System.out.println(resultList.toString());
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < Integer.parseInt(count); i++) {
            Map<String, LocalDateTime> firstDateTimeMap = getQueryDateMap(firstYear, firstMonth);
            int intervalMin = resultList.get(i).get(0);
            int intervalMax = resultList.get(i).get(1);
            AngleAnalysisDto firstAngleDto = angleAnalysisService.angleAnalysisDto(null, intervalMin, intervalMax, firstDateTimeMap.get("beginDateTime"), firstDateTimeMap.get("endDateTime"));
            dataMap.put("angle" + i, firstAngleDto);
        }
        return Result.success(dataMap);
    }
}
