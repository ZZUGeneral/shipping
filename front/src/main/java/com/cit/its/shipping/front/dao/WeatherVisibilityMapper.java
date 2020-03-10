package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cit.its.shipping.front.dto.WeatherVisibilityAnalysisDto;
import com.cit.its.shipping.front.dto.WeatherVisibilityStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherVisibility;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherVisibilityMapper extends BaseMapper<WeatherVisibility> {

    @Select("select max(value) as max, min(value) as min, avg(value) as avg, var_pop(value) as varPop, stddev_pop(value) as stdDevPop," +
            " var_samp(value) as varSamp, stddev_samp(value) as stdDevSamp from msg_weather_visibility ${ew.customSqlSegment}")
    WeatherVisibilityStatisticsDto weatherVisibilityStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(value) as count from msg_weather_visibility ${ew.customSqlSegment}")
    WeatherVisibilityAnalysisDto weatherVisibilityAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);
}
