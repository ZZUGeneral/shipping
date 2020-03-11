package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cit.its.shipping.front.entity.Event;
import com.cit.its.shipping.front.vo.EventDetailVO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 杨贺龙
 * @name EventMapper
 * @create 2019-12-02 14:20
 * @description: Event处理Mapper,封装对Event的操作
 */
@Repository
public interface EventMapper extends BaseMapper<Event> {
    // 根据不同的判断条件(事件名称,等级,设备名,数据项,开始时间,结束时间,页数)筛选出事件列表
    List<EventDetailVO> selectDetailByFactors(String event_name, int grade, String equip, String data, LocalDateTime beginTime, LocalDateTime endTime, int page);
}
