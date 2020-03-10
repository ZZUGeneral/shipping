package com.cit.its.shipping.front.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 雨量信息统计DTO
 */
@Data
public class WeatherRainfallStatisticsDto {

    /**
     * 最大值
     */
    private Float max;

    /**
     * 最小值
     */
    private Float min;

    /**
     * 平均值
     */
    private Float avg;

    /**
     * 总体方差
     */
    private Float varPop;

    /**
     * 总体标准差
     */
    private Float stdDevPop;

    /**
     * 样本方差
     */
    private Float varSamp;

    /**
     * 样本标准差
     */
    private Float stdDevSamp;

    /**
     * 主题
     */
    private String topic;

    /**
     * 开始时间
     */
    private LocalDateTime beginDateTime;

    /**
     * 结束时间
     */
    private LocalDateTime endDateTime;
}
