package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.entity.Event;


import java.util.List;

/**
 * @author 杨贺龙
 * @name EventService
 * @create 2019-12-02 14:22
 * @description:
 */
public interface EventService extends IService<Event> {

    List<Event> eventPageData(String beginDateTime, String endDateTime, int grade, Integer currentPage, Integer size);
}
