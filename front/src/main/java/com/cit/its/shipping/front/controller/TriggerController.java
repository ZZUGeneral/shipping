package com.cit.its.shipping.front.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cit.its.shipping.front.entity.Trigger;
import com.cit.its.shipping.front.service.TriggerService;
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
 * @name TriggerController
 * @create 2019-12-02 9:35
 * @description: 告警
 */
@Controller
@Slf4j
@RequestMapping("/trigger")
@ApiIgnore
public class TriggerController {
    @Autowired
    private TriggerService triggerService;

    /**
     * @return java.lang.String
     * @Author 杨贺龙
     * @Description //TODO  添加告警
     * @Date 9:39 2019/12/2
     * @Name createTrigger
     * @Param [trigger]
     **/
    @ApiOperation(value = "添加新的告警")
    @ApiImplicitParam(name = "告警实体", required = true)
    @RequestMapping("/createTrigger")
    @ResponseBody
    public R createTrigger(@RequestParam Trigger trigger) {
        int result = this.triggerService.createTrigger(trigger);
        if (result == 0) {
            return R.failed("添加新的告警失败!");
        }
        return R.ok("添加新的告警成功");
    }

    @ApiOperation(value = "删除告警", notes = "根据告警ID删除告警")
    @ApiImplicitParam(name = "trigger_id", value = "告警ID", dataType = "int", required = true)
    @RequestMapping("/deleteTrigger")
    public R deleteTrigger(@RequestParam int trigger_id) {
        int result = this.triggerService.dropTrigger(trigger_id);
        if (result == 0) {
            return R.failed("删除告警失败!");
        }
        return R.ok("删除告警成功");
    }

    @ApiOperation(value = "更新告警", notes = "根据告警ID更新告警")
    @ApiImplicitParam(name = "trigger", value = "告警实体", dataType = "Trigger", required = true)
    @RequestMapping("/updateTrigger")
    public R updateTrigger(@RequestParam Trigger trigger) {
        int result = this.triggerService.replaceTrigger(trigger);
        if (result == 0) {
            return R.failed("更新告警失败!");
        }
        return R.ok("更新告警成功");
    }

    @ApiOperation(value = "查询告警", notes = "根据名字模糊查询,等级,设备名,数据项名查询告警列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "trigger_name", value = "告警名字", dataType = "String", required = false),
            @ApiImplicitParam(name = "grade", value = "告警级别", dataType = "int", required = false),
            @ApiImplicitParam(name = "equip", value = "告警设备", dataType = "String", required = false),
            @ApiImplicitParam(name = "data", value = "告警数据项", dataType = "String", required = false),
            @ApiImplicitParam(name = "page_no", value = "页数", dataType = "long", required = true),
    })
    @RequestMapping("/selectTrigger")
    @ResponseBody
    public R selectTriggers(String trigger_name, int grade, String equip, String data, long page_no) {
        QueryWrapper<Trigger> wrapper = new QueryWrapper();
        if (trigger_name != null)
            wrapper.like("trigger_name", trigger_name);
        if (grade != 0) wrapper.eq("grade", grade);
        if (equip != null) wrapper.eq("equip", equip);
        if (data != null) wrapper.eq("data", data);
        wrapper.orderByDesc("id");
        Page<Trigger> page = new Page<>(page_no, 15);
        IPage<Trigger> iPage = this.triggerService.getBaseMapper().selectPage(page, wrapper);
        List<Trigger> triggerList = iPage.getRecords();
        return R.ok(triggerList);
    }
}
