package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cit.its.shipping.front.entity.Event;
import org.springframework.stereotype.Repository;

/**
 * @author 杨贺龙
 * @name EventMapper
 * @create 2019-12-02 14:20
 * @description: Event处理Mapper, 封装对Event的操作
 */
@Repository
public interface EventMapper extends BaseMapper<Event> {

}
