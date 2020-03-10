package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.TiltAnalysisDto;
import com.cit.its.shipping.front.entity.Tilt;

import java.time.LocalDateTime;

public interface TiltAnalysisService extends IService<Tilt> {

    TiltAnalysisDto x1TiltAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    TiltAnalysisDto y1TiltAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    TiltAnalysisDto x2TiltAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    TiltAnalysisDto y2TiltAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

}
