package com.cit.its.shipping.front.service;


import com.cit.its.shipping.front.entity.EquipDetailData;
import com.cit.its.shipping.front.entity.EquipStatus;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.util.List;

/**
 * @author 杨贺龙
 * @name OutPutService
 * @create 2019-12-26 17:17
 * @description:
 */
public interface OutPutService {

    /*//获取运行状态信息列表
    public List<EquipStatus> getEquipStatusList(String name, long beginTime, long endTime, int pageNo);
*/
    //获取时间区间内的详细数据
    public List<EquipDetailData> getDetailData(long id, long beginTime, long endTime);

    //导出运行状态Excel
    public HSSFWorkbook outputRunningStatusXlsx(String name, long beginTime, long endTime);

    //导出运行状态Word
    public XWPFDocument outputRunningStatusDoc(String name, long beginTime, long endTime);


}
