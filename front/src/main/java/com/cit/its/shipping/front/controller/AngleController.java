package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.entity.Angle;
import com.cit.its.shipping.front.service.AngleService;
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
 * @Description: 角度传感器Controller
 */
@Api(value = "角度Controller", tags = "角度接口")
@RestController
public class AngleController {

    @Autowired
    private AngleService angleService;


    @ApiOperation(value = "获取角度历史数据", notes = "获取角度历史数据")
    @PostMapping("history/angle")
    public Result historyAngleData(@RequestParam String topic, @RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<Angle> angleList = angleService.angleHistory(topic, beginDateTime, endDateTime);
//        angleList = angleList.subList(0, 1000);
        if (angleList.isEmpty()) {
            return Result.fail(200, "没有查询到数据，请再次确认查询条件");
        }
        return Result.success(angleList);
    }
}
