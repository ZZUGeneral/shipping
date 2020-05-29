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
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {
    @Autowired
    EventMapper eventMapper;

    @Override
    public IPage<Event> eventPageData(String beginDateTime, String endDateTime, int grade, Integer currentPage, Integer size) {
        Page page = new Page(currentPage, size);
        LambdaQueryWrapper<Event> wrapper = Wrappers.lambdaQuery();
        if (StrUtil.isNotEmpty(beginDateTime)) {
            LocalDateTime beginTime = LocalDateTime.parse(beginDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            wrapper.ge(Event::getCreateTime, beginTime);
        }
        if (StrUtil.isNotEmpty(endDateTime)) {
            LocalDateTime endTime = LocalDateTime.parse(endDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            wrapper.ge(Event::getCreateTime, endTime);
        }
        if (grade == 0) {
            wrapper.eq(Event::getGrade, grade);
        }
        wrapper.orderByAsc(Event::getCreateTime);
        IPage<Event> iPage = eventMapper.selectPage(page, wrapper);
        return iPage;
    }

}
