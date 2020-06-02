package com.cit.its.shipping.front.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.cit.its.shipping.front.entity.Event;
import com.cit.its.shipping.front.service.EventService;
import com.cit.its.shipping.front.vo.PageVo;
import com.cit.its.shipping.front.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 杨贺龙
 * @name EventController
 * @create 2019-12-02 14:19
 * @description: 触发事件的处理
 */
@Controller
@Slf4j
@RequestMapping("/event")
@Api(value = "事件处理controller", tags = "事件处理接口")
public class EventController {
    @Autowired
    EventService eventService;

    @ApiOperation(value = "触发事件详情列表", notes = "根据不同的判断条件(等级,开始时间,结束时间,页数)筛选出事件列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "beginTime", value = "事件触发时间范围起始时间", dataType = "String", required = false),
            @ApiImplicitParam(name = "endTime", value = "事件触发时间范围结束时间", dataType = "String", required = false),
            @ApiImplicitParam(name = "grade", value = "告警级别", dataType = "int", required = false),
            @ApiImplicitParam(name = "page", value = "页数", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "size", value = "数据项数", dataType = "Integer", required = true)
    })
    @PostMapping("/pageData")
    @ResponseBody
    public Result eventPageData(@RequestParam String beginTime, @RequestParam String endTime, @RequestParam Integer grade, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        IPage<Event> eventIPage = this.eventService.eventPageData(beginTime, endTime, grade, page, size);
        PageVo<Event> pageVo = new PageVo<Event>(page, size, eventIPage.getTotal(), eventIPage.getRecords());
        return Result.success(pageVo);
    }


    @ApiOperation(value = "分配人员处理事件", notes = "根据事件ID分配人员(编号)处理事件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "event_no", value = "事件ID", dataType = "long", required = true),
            @ApiImplicitParam(name = "deal_no", value = "处理人员编号", dataType = "long", required = true)
    })
    @PostMapping("/dealEvent")
    @ResponseBody
    public R updateDealNoForEvent(@RequestParam Long event_no, @RequestParam Long deal_no) {
        int result = this.eventService.updateDealNo(event_no, deal_no);
        if (result == 0) {
            return R.failed("分配失败");
        }
        return R.ok("分配成功");
    }
}
