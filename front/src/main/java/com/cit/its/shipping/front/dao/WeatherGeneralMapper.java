package com.cit.its.shipping.front.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.cit.its.shipping.front.dto.TiltAnalysisDto;
import com.cit.its.shipping.front.dto.WeatherGeneralAnalysisDto;
import com.cit.its.shipping.front.dto.WeatherGeneralStatisticsDto;
import com.cit.its.shipping.front.entity.WeatherGeneral;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface WeatherGeneralMapper extends BaseMapper<WeatherGeneral> {

    @Select("select max(temperature) as max, min(temperature) as min, avg(temperature) as avg, var_pop(temperature) as varPop, stddev_pop(temperature) as stdDevPop," +
            " var_samp(temperature) as varSamp, stddev_samp(temperature) as stdDevSamp from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralStatisticsDto temperatureStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select max(humidity) as max, min(humidity) as min, avg(humidity) as avg, var_pop(humidity) as varPop, stddev_pop(humidity) as stdDevPop," +
            " var_samp(humidity) as varSamp, stddev_samp(humidity) as stdDevSamp from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralStatisticsDto humidityStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select max(air_pressure) as max, min(air_pressure) as min, avg(air_pressure) as avg, var_pop(air_pressure) as varPop, stddev_pop(air_pressure) as stdDevPop," +
            " var_samp(air_pressure) as varSamp, stddev_samp(air_pressure) as stdDevSamp from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralStatisticsDto airPressureStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select max(wind_speed) as max, min(wind_speed) as min, avg(wind_speed) as avg, var_pop(wind_speed) as varPop, stddev_pop(wind_speed) as stdDevPop," +
            " var_samp(wind_speed) as varSamp, stddev_samp(wind_speed) as stdDevSamp from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralStatisticsDto windSpeedStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select max(wind_direction) as max, min(wind_direction) as min, avg(wind_direction) as avg, var_pop(wind_direction) as varPop, stddev_pop(wind_direction) as stdDevPop," +
            " var_samp(wind_direction) as varSamp, stddev_samp(wind_direction) as stdDevSamp from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralStatisticsDto windDirectionStatistics(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(temperature) as count from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralAnalysisDto temperatureAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(humidity) as count from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralAnalysisDto humidityAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(air_pressure) as count from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralAnalysisDto airPressureAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(wind_speed) as count from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralAnalysisDto windSpeedAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);

    @Select("select count(wind_direction) as count from msg_weather_general ${ew.customSqlSegment}")
    WeatherGeneralAnalysisDto windDirectionAnalysis(@Param(Constants.WRAPPER) Wrapper wrapper);
}
