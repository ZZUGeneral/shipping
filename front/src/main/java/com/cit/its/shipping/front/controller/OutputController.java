package com.cit.its.shipping.front.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.cit.its.shipping.front.entity.EquipDetailData;
import com.cit.its.shipping.front.entity.EquipStatus;
import com.cit.its.shipping.front.service.OutPutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 杨贺龙
 * @name OutputController
 * @create 2019-11-28 10:40
 * @description:
 */
@Api(value = "export", tags = "导出数据")
@Controller
@Slf4j
@RequestMapping("/export")
@ApiIgnore
public class OutputController {
    @Autowired
    private OutPutService outPutService;


/*    @ApiOperation(value = "运行状态列表", notes = "根据条件筛选运行状态条件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "设备名称", dataType = "String", required = true),
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "Integer", required = true),
            @ApiImplicitParam(name = "beginTime", value = "起始时间", dataType = "Long", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Long", required = true)
    })
    @RequestMapping(value = "/getEquipStatusList")
    @ResponseBody
    private R getEquipStatusList(String name, int pageNo, long beginTime, long endTime) {
        List<EquipStatus> list = this.outPutService.getEquipStatusList(name, beginTime, endTime, pageNo);
        if (list.isEmpty()) {
            return R.failed("没有找到符合条件的数据,请重新设置筛选条件.");
        }
        return R.ok(list);
    }*/


    @ApiOperation(value = "获取设备数据", notes = "根据条件获取设备在某个时间段内的数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "设备ID", dataType = "Long", required = true),
            @ApiImplicitParam(name = "type", value = "数据类型", dataType = "String", required = true),
            @ApiImplicitParam(name = "beginTime", value = "起始时间", dataType = "Long", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Long", required = true)
    })
    @RequestMapping(value = "/getDetailData")
    @ResponseBody
    private R getDetailData(@RequestParam long id, @RequestParam String type, @RequestParam long beginTime, @RequestParam long endTime) {
        List<EquipDetailData> detailDataList = this.outPutService.getDetailData(id, beginTime, endTime);
        return R.ok(detailDataList);
    }


    @ApiOperation(value = "导出运行状态报表", notes = "空载、超载、正常运行的次数，占比等，数据报表可下钻至某个时间断片的详细数据")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "设备名称", dataType = "String", required = true),
            @ApiImplicitParam(name = "beginTime", value = "起始时间", dataType = "Long", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Long", required = true),
            @ApiImplicitParam(name = "isDetail", value = "详情数据的导出与否", dataType = "Boolean", required = true)
    })
    @RequestMapping(value = "runningStatusXlsx")
    @ResponseBody
    public void runningStatusXlsx(HttpServletRequest request, HttpServletResponse response, @RequestParam String name, @RequestParam long beginTime, @RequestParam long endTime) {
        String filename = "运行状态" + new SimpleDateFormat("yyyyMMDDhh24mmssSSS")
                .format(System.currentTimeMillis()) + ".xlsx";

        OutputStream os = null;
        HSSFWorkbook excel = null;
        try {
            excel = outPutService.outputRunningStatusXlsx(name, beginTime, endTime);
            this.setResponseHeader(response, filename);
            os = response.getOutputStream();
            excel.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @ApiOperation(value = "导出运行状态报表", notes = "空载、超载、正常运行的次数，占比等，数据报表可下钻至某个时间断片的详细数据")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "设备名称", dataType = "String", required = true),
            @ApiImplicitParam(name = "beginTime", value = "起始时间", dataType = "Long", required = true),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "Long", required = true),
            @ApiImplicitParam(name = "isDetail", value = "详情数据的导出与否", dataType = "Boolean", required = true)
    })
    @RequestMapping(value = "runningStatusDoc")
    @ResponseBody
    public void runningStatusDoc(HttpServletRequest request, HttpServletResponse response, @RequestParam String name, @RequestParam long beginTime, @RequestParam long endTime) {
        String filename = "运行状态" + new SimpleDateFormat("yyyyMMDDhh24mmssSSS")
                .format(System.currentTimeMillis()) + ".doc";

        OutputStream os = null;
        XWPFDocument word = null;
        try {
            word = outPutService.outputRunningStatusDoc(name, beginTime, endTime);
            this.setResponseHeader(response, filename);
            os = response.getOutputStream();
            word.write(os);
            os.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void setResponseHeader(HttpServletResponse response, String filename) {
        try {
            try {
                filename = new String(filename.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Controller", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
