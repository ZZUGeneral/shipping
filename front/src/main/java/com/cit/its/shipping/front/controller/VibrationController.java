package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.entity.Vibration;
import com.cit.its.shipping.front.service.VibrationService;
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
 * @Description: 震动传感器Controller
 */
@RestController
@ApiIgnore
public class VibrationController {

    @Autowired
    private VibrationService vibrationService;

    @ApiOperation(value = "获取震动历史数据", notes = "获取震动历史数据")
    @PostMapping("history/vibration")
    public Result historyVibrationData(@RequestParam String topic, @RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<Vibration> vibrationList = vibrationService.vibrationHistory(topic, beginDateTime, endDateTime);
//        vibrationList = vibrationList.subList(0, 1000);
        /*System.out.println(vibrationList.toString());
        Map<String, List<Vibration>> vibrationMap = vibrationList.stream().collect(Collectors.groupingBy(Vibration::getTopic));
        VibrationStatisticsDto vVibrationStatisticsDto = vibrationService.vVibrationStatistics(null, beginDateTime, endDateTime);
        VibrationStatisticsDto hVibrationStatisticsDto = vibrationService.hVibrationStatistics(null, beginDateTime, endDateTime);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("vibration", vibrationMap);
        dataMap.put("sta_v", vVibrationStatisticsDto);
        dataMap.put("sta_h", hVibrationStatisticsDto);*/
        if (vibrationList.isEmpty()) {
            return Result.fail(200,"没有查询到数据，请再次确认查询条件");
        }
        return Result.success(vibrationList);
    }
}
