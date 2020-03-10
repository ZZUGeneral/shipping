package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cit.its.shipping.front.dto.WeatherRainfallAnalysisDto;
import com.cit.its.shipping.front.dto.WeatherRainfallStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherRainfall;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherRainfallMapper extends BaseMapper<WeatherRainfall> {

    @Select("select max(value) as max, min(value) as min, avg(value) as avg, var_pop(value) as varPop, stddev_pop(value) as stdDevPop," +
            " var_samp(value) as varSamp, stddev_samp(value) as stdDevSamp from msg_weather_rainfall ${ew.customSqlSegment}")
    WeatherRainfallStatisticsDto weatherRainfallStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(value) as count from msg_weather_rainfall ${ew.customSqlSegment}")
    WeatherRainfallAnalysisDto weatherRainfallAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);
}
