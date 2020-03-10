package com.cit.its.shipping.front.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cit.its.shipping.front.dto.VibrationAnalysisDto;
import com.cit.its.shipping.front.entity.Vibration;

import java.time.LocalDateTime;

public interface VibrationAnalysisService extends IService<Vibration> {

    VibrationAnalysisDto hVibrationAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);

    VibrationAnalysisDto vVibrationAnalysis(String topic, String dataItem, int leftInterval, int RightInterval, LocalDateTime beginDateTime, LocalDateTime endDateTime);
}
