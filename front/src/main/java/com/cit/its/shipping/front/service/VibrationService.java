package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.VibrationStatisticsDto;
import com.cit.its.shipping.front.entity.Vibration;

import java.time.LocalDateTime;
import java.util.List;

public interface VibrationService extends IService<Vibration> {

    List<Vibration> vibrationHistory(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    VibrationStatisticsDto hVibrationStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    VibrationStatisticsDto vVibrationStatistics(String topic, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    void insertVibration(String topic, String jsonContent);
}
