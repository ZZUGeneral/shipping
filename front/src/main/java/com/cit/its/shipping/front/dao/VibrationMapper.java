package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cit.its.shipping.front.dto.VibrationAnalysisDto;
import com.cit.its.shipping.front.dto.VibrationStatisticsDto;
import com.cit.its.shipping.front.entity.Vibration;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface VibrationMapper extends BaseMapper<Vibration> {

    @Select("select max(value_h) as max, min(value_h) as min, avg(value_h) as avg, var_pop(value_h) as varPop, stddev_pop(value_h) as stdDevPop," +
            " var_samp(value_h) as varSamp, stddev_samp(value_h) as stdDevSamp from msg_vibration ${ew.customSqlSegment}")
    VibrationStatisticsDto hVibrationStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select max(value_v) as max, min(value_v) as min, avg(value_v) as avg, var_pop(value_v) as varPop, stddev_pop(value_v) as stdDevPop," +
            " var_samp(value_v) as varSamp, stddev_samp(value_v) as stdDevSamp from msg_vibration ${ew.customSqlSegment}")
    VibrationStatisticsDto vVibrationStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(value_h) as count from msg_vibration ${ew.customSqlSegment}")
    VibrationAnalysisDto hVibrationAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(value_v) as count from msg_vibration ${ew.customSqlSegment}")
    VibrationAnalysisDto vVibrationAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);
}
