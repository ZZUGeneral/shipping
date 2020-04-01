package com.cit.its.shipping.front.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cit.its.shipping.front.dao.EventMapper;
import com.cit.its.shipping.front.entity.Event;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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
    public List<Event> eventPageData(String beginDateTime, String endDateTime, int grade, Integer currentPage, Integer size) {
        Page page = new Page(currentPage, size);
        LocalDateTime beginTime = LocalDateTime.parse(beginDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endTime = LocalDateTime.parse(endDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LambdaQueryWrapper<Event> wrapper = queryCondition(grade, beginTime, endTime);
        wrapper.orderByAsc(Event::getCreaeteTime);
        IPage<Event> iPage = eventMapper.selectPage(page, wrapper);
        return iPage.getRecords();
    }

    private LambdaQueryWrapper queryCondition(int grade, LocalDateTime beginDateTime, LocalDateTime endDateTime) {
        LambdaQueryWrapper<Event> wrapper = Wrappers.lambdaQuery();
        if (grade == 0) {
            wrapper.eq(Event::getGrade, grade);
        }
        if (beginDateTime != null) {
            wrapper.ge(Event::getCreaeteTime, beginDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        if (endDateTime != null) {
            wrapper.le(Event::getCreaeteTime, endDateTime.toEpochSecond(ZoneOffset.of("+8")));
        }
        return wrapper;
    }
}
