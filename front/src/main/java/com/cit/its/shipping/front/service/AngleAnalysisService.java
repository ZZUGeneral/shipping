package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.AngleAnalysisDto;
import com.cit.its.shipping.front.dto.AngleStatisticsDto;
import com.cit.its.shipping.front.entity.Angle;

import java.time.LocalDateTime;
import java.util.List;

public interface AngleAnalysisService extends IService<Angle> {

    AngleAnalysisDto angleAnalysisDto(String topic, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

}
