package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.entity.Event;
import com.cit.its.shipping.front.vo.EventDetailVO;


import java.util.List;

/**
 * @author 杨贺龙
 * @name EventService
 * @create 2019-12-02 14:22
 * @description:
 */
public interface EventService extends IService<Event> {
    // 根据不同的判断条件(事件名称,等级,设备名,数据项,开始时间,结束时间,页数)筛选出事件列表
    List<EventDetailVO> selectDetailByFactors(String event_name, int grade, String equip, String data, String beginTimeStr, String endTimeStr, int page);

}
