package com.cit.its.shipping.front.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 倾斜传感器信息统计DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TiltStatisticsDto {

    /**
     * 最大值
     */
//    private Float value_x_max;
//
//    /**
//     * 最小值
//     */
//    private Float value_x_min;
//
//    /**
//     * 平均值
//     */
//    private Float value_x_avg;
//
//    /**
//     * 总体方差
//     */
//    private Float value_x_varPop;
//
//    /**
//     * 总体标准差
//     */
//    private Float value_x_stdDevPop;
//
//    /**
//     * 样本方差
//     */
//    private Float value_x_varSamp;
//
//    /**
//     * 样本标准差
//     */
//    private Float value_x_stdDevSamp;

//    /**
//     * 最大值
//     */
//    private Float value_y_max;
//
//    /**
//     * 最小值
//     */
//    private Float value_y_min;
//
//    /**
//     * 平均值
//     */
//    private Float value_y_avg;
//
//    /**
//     * 总体方差
//     */
//    private Float value_y_varPop;
//
//    /**
//     * 总体标准差
//     */
//    private Float value_y_stdDevPop;
//
//    /**
//     * 样本方差
//     */
//    private Float value_y_varSamp;
//
//    /**
//     * 样本标准差
//     */
//    private Float value_y_stdDevSamp;
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
