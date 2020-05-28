package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.AngleStatisticsDto;
import com.cit.its.shipping.front.entity.Angle;

import java.time.LocalDateTime;
import java.util.List;

public interface AngleService extends IService<Angle> {

    List<Angle> angleHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    AngleStatisticsDto angleStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    void insertAngle(String topic, String jsonContent);

}
