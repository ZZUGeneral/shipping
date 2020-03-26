package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.EventMapper;
import com.cit.its.shipping.front.entity.Event;
import com.cit.its.shipping.front.service.EventService;
import com.cit.its.shipping.front.vo.EventDetailVO;
import org.apache.poi.ss.formula.functions.Even;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author 杨贺龙
 * @name EventServiceImpl
 * @create 2019-12-02 14:22
 * @description:
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
    @Autowired
    EventMapper eventMapper;

    @Override
    public List<EventDetailVO> selectDetailByFactors(String event_name, int grade, String equip, String data, String beginTimeStr, String endTimeStr, int page) {
        LocalDateTime beginTime = LocalDateTime.parse(beginTimeStr);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr);
        List<EventDetailVO> detailVOList = this.eventMapper.selectDetailByFactors(event_name, grade, equip, data, beginTime, endTime, page);
        return detailVOList;
    }

    @Override
    public List<Event> eventPageData(Date beginTime, Date endTime, int grade, Integer currentPage, Integer size) {
        Page page = new Page(currentPage, size);
        QueryWrapper<Event> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(beginTime)) {
            wrapper.ge("createTime", beginTime);
        }
        if (ObjectUtil.isNotNull(endTime)) {
            wrapper.le("createTime", endTime);
        }
        if (ObjectUtil.isNotNull(grade) && grade != 0) {
            wrapper.eq("grade", grade);
        }
        wrapper.orderByAsc("createTime");
        IPage<Event> iPage = eventMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }
}
