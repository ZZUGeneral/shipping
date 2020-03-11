package com.cit.its.shipping.front.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.cit.its.shipping.front.entity.Event;
import com.cit.its.shipping.front.service.EventService;
import com.cit.its.shipping.front.vo.EventDetailVO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @author 杨贺龙
 * @name EventController
 * @create 2019-12-02 14:19
 * @description: 触发事件的处理
 */
@ApiIgnore
@Controller
@Slf4j
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventService eventService;

    @ApiOperation(value = "触发事件详情列表", notes = "根据不同的判断条件(事件名称,等级,设备名,数据项,开始时间,结束时间,页数)筛选出事件列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "event_name", value = "事件名字", dataType = "String", required = false),
            @ApiImplicitParam(name = "grade", value = "告警级别", dataType = "int", required = false),
            @ApiImplicitParam(name = "equip", value = "告警设备", dataType = "String", required = false),
            @ApiImplicitParam(name = "data", value = "告警数据项", dataType = "String", required = false),
            @ApiImplicitParam(name = "beginTimeStr", value = "事件触发时间范围起始时间", dataType = "String", required = false),
            @ApiImplicitParam(name = "endTimeStr", value = "事件触发时间范围结束时间", dataType = "String", required = false),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "String", required = true)
    })
    @RequestMapping("/selectDetailByFactors")
    @ResponseBody
    public R selectDetailByFactors(@RequestParam String event_name, @RequestParam int grade, @RequestParam String equip, @RequestParam String data, @RequestParam String beginTimeStr, @RequestParam String endTimeStr, @RequestParam int page) {
        if (beginTimeStr != null && endTimeStr == null) {
            endTimeStr = Long.toString(System.currentTimeMillis());
        }
        if (beginTimeStr == null && endTimeStr != null) {
            beginTimeStr = Long.toString(0);
        }
        List<EventDetailVO> detailList = this.eventService.selectDetailByFactors(event_name, grade, equip, data, beginTimeStr, endTimeStr, page);
        return R.ok(detailList);
    }

    @ApiOperation(value = "分配人员处理事件", notes = "根据事件ID分配人员(编号)处理事件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "event_no", value = "事件ID", dataType = "long", required = true),
            @ApiImplicitParam(name = "deal_no", value = "处理人员编号", dataType = "long", required = true)
    })
    @RequestMapping("/dealEvent")
    @ResponseBody
    public R updateDealNoForEvent(@RequestParam long event_no, @RequestParam long deal_no) {
        Event event = new Event();
        event.setEvent_id(event_no);
        event.setDealNo(deal_no);
        int result = this.eventService.getBaseMapper().updateById(event);
        if (result == 0) {
            return R.failed("分配失败");
        }
        return R.ok("分配成功");
    }
}
