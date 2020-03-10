package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cit.its.shipping.front.dto.AngleAnalysisDto;
import com.cit.its.shipping.front.dto.AngleStatisticsDto;
import com.cit.its.shipping.front.entity.Angle;
import com.cit.its.shipping.front.entity.WaterLevel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface AngleMapper extends BaseMapper<Angle> {

    @Select("select max(value) as max, min(value) as min, avg(value) as avg, var_pop(value) as varPop, stddev_pop(value) as stdDevPop," +
            " var_samp(value) as varSamp, stddev_samp(value) as stdDevSamp from msg_angle ${ew.customSqlSegment}")
    AngleStatisticsDto angleStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(value) as count from msg_angle ${ew.customSqlSegment}")
    AngleAnalysisDto angleAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

}
