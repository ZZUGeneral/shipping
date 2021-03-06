package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
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

    IPage<Event> eventPageData(String beginDateTime, String endDateTime, Integer grade, Integer currentPage, Integer size);

    public int updateDealNo(Long event_no, Long deal_no);
}
