package com.cit.its.shipping.front.controller;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cit.its.shipping.front.entity.Trigger;
import com.cit.its.shipping.front.service.TriggerService;
import com.cit.its.shipping.front.vo.PageVo;
import com.cit.its.shipping.front.vo.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 杨贺龙
 * @name TriggerController
 * @create 2019-12-02 9:35
 * @description: 触发器
 */
@RestController
@Slf4j
@RequestMapping("/trigger")
public class TriggerController {
    @Autowired
    private TriggerService triggerService;

    /**
     * @return java.lang.String
     * @Author 杨贺龙
     * @Description //TODO  添加触发器
     * @Date 9:39 2019/12/2
     * @Name createTrigger
     * @Param [trigger]
     **/


    @ApiOperation(value = "添加新的触发器")
    @ApiImplicitParam(name = "触发器实体", required = true)
    @PostMapping("/createTrigger")
    @ResponseBody
    public Result createTrigger(@RequestBody Trigger trigger) {
        System.out.println(trigger);
        if (ObjectUtil.isNull(trigger)) {
            return Result.fail(500, "网络异常!");
        }
        int result = this.triggerService.createTrigger(trigger);
        if (result == 0) {
            return Result.fail(400, "添加新的触发器失败!");
        }
        return Result.success("添加新的触发器成功");
    }


    @ApiOperation(value = "删除触发器", notes = "根据触发器ID删除触发器")
    @ApiImplicitParam(name = "triggerName", value = "触发器名称", dataType = "String", required = true)
    @RequestMapping("/deleteTrigger")
    public Result deleteTrigger(@RequestParam String triggerName) {
        int result = this.triggerService.dropTrigger(triggerName);
        if (result == 0) {
            return Result.fail(400, "删除触发器失败!");
        }
        return Result.success("删除触发器成功");
    }

    @ApiOperation(value = "查找触发器", notes = "根据触发器ID查找触发器")
    @ApiImplicitParam(name = "triggerName", value = "触发器名称", dataType = "String", required = true)
    @RequestMapping("/deleteTrigger")
    public Result selectTrigger(@RequestParam String triggerName) {
        int result = this.triggerService.dropTrigger(triggerName);
        if (result == 1) {
            return Result.fail(1, "");
        }
        return Result.success(0);
    }

    @ApiOperation(value = "更新触发器", notes = "根据触发器ID更新触发器")
    @ApiImplicitParam(name = "trigger", value = "触发器实体", dataType = "Trigger", required = true)
    @RequestMapping("/updateTrigger")
    public Result updateTrigger(@RequestParam Trigger trigger) {
        int result = this.triggerService.replaceTrigger(trigger);
        if (result == 0) {
            return Result.fail(400, "更新触发器失败!");
        }
        return Result.success("更新触发器成功");
    }

    @ApiOperation(value = "查询触发器", notes = "根据名字模糊查询,等级,设备名,数据项名查询触发器列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "trigger", value = "触发器", dataType = "Trigger", required = false),
            @ApiImplicitParam(name = "page", value = "页码", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "size", value = "每页数据量", dataType = "Integer", required = true),
    })
    @PostMapping("/trigger/pageData")
    public Result triggerPageData(Trigger trigger, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        IPage<Trigger> triggerIPage = this.triggerService.triggerPageData(trigger, page, size);
        PageVo<Trigger> pageVo = new PageVo<Trigger>(page, size, triggerIPage.getTotal(), triggerIPage.getRecords());
        return Result.success(pageVo);
    }
}
