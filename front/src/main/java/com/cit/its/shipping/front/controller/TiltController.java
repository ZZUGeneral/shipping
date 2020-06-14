package com.cit.its.shipping.front.controller;

import com.cit.its.shipping.front.entity.Tilt;
import com.cit.its.shipping.front.service.TiltService;
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
 * @Description: 倾斜传感器Controller
 */
@RestController
@Api(value = "倾斜Controller", tags = "倾斜接口")
public class TiltController {

    @Autowired
    private TiltService tiltService;

    @ApiOperation(value = "获取倾斜历史数据", notes = "获取倾斜历史数据")
    @PostMapping("history/tilt")
    public Result historyTiltData(@RequestParam String topic, @RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<Tilt> tiltList = tiltService.tiltHistory(topic, beginDateTime, endDateTime);
//        tiltList = tiltList.subList(0, 1000);
//        for (Tilt tilt : tiltList) {
//            tilt.setValueX(tilt.getValue1X(), tilt.getValue2X());
//            tilt.setValueY(tilt.getValue1Y(), tilt.getValue2Y());
//        }
        return Result.success(tiltList);
    }
}
