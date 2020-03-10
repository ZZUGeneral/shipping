package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.TiltStatisticsDto;
import com.cit.its.shipping.front.entity.Tilt;

import java.time.LocalDateTime;
import java.util.List;

public interface TiltService extends IService<Tilt> {

    List<Tilt> tiltHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    TiltStatisticsDto x1TiltStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    TiltStatisticsDto x2TiltStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    TiltStatisticsDto y1TiltStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    TiltStatisticsDto y2TiltStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

}
