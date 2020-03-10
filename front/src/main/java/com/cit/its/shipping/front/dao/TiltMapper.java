package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cit.its.shipping.front.dto.TiltAnalysisDto;
import com.cit.its.shipping.front.dto.TiltStatisticsDto;
import com.cit.its.shipping.front.entity.Tilt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface TiltMapper extends BaseMapper<Tilt> {

    @Select("select max(val1_y) as max, min(val1_y) as min, avg(val1_y) as avg, var_pop(val1_y) as varPop, stddev_pop(val1_y) as stdDevPop," +
            " var_samp(val1_y) as varSamp, stddev_samp(val1_y) as stdDevSamp from msg_tilt ${ew.customSqlSegment}")
    TiltStatisticsDto y1TiltStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select max(val1_x) as max, min(val1_x) as min, avg(val1_x) as avg, var_pop(val1_x) as varPop, stddev_pop(val1_x) as stdDevPop," +
            " var_samp(val1_x) as varSamp, stddev_samp(val1_x) as stdDevSamp from msg_tilt ${ew.customSqlSegment}")
    TiltStatisticsDto x1TiltStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select max(val2_y) as max, min(val2_y) as min, avg(val2_y) as avg, var_pop(val2_y) as varPop, stddev_pop(val2_y) as stdDevPop," +
            " var_samp(val2_y) as varSamp, stddev_samp(val2_y) as stdDevSamp from msg_tilt ${ew.customSqlSegment}")
    TiltStatisticsDto y2TiltStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select max(val2_x) as max, min(val2_x) as min, avg(val2_x) as avg, var_pop(val2_x) as varPop, stddev_pop(val2_x) as stdDevPop," +
            " var_samp(val2_x) as varSamp, stddev_samp(val2_x) as stdDevSamp from msg_tilt ${ew.customSqlSegment}")
    TiltStatisticsDto x2TiltStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(val1_x) as count from msg_tilt ${ew.customSqlSegment}")
    TiltAnalysisDto x1TiltAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(val1_y) as count from msg_tilt ${ew.customSqlSegment}")
    TiltAnalysisDto y1TiltAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(val2_x) as count from msg_tilt ${ew.customSqlSegment}")
    TiltAnalysisDto x2TiltAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(val2_y) as count from msg_tilt ${ew.customSqlSegment}")
    TiltAnalysisDto y2TiltAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);
}
