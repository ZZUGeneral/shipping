package com.cit.its.shipping.front.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cit.its.shipping.front.dao.*;
import com.cit.its.shipping.front.entity.*;
import com.cit.its.shipping.front.service.OutPutService;
import com.cit.its.shipping.front.util.ExcelUtil;
import com.cit.its.shipping.front.util.WordUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author 杨贺龙
 * @name OutPutServiceImpl
 * @create 2019-12-26 17:17
 * @description:
 */
@Service
public class OutPutServiceImpl implements OutPutService {
    @Autowired
    WaterLevelMapper waterLevelMapper;
    AngleMapper angleMapper;
    TiltMapper tiltMapper;
    VibrationMapper vibrationMapper;
    WeatherGeneralMapper weatherGeneralMapper;
    WeatherRainfallMapper weatherRainfallMapper;
    WeatherVisibilityMapper weatherVisibilityMapper;
    SensorMapper sensorMapper;


    @Override
    public List<EquipStatus> getEquipStatusList(String name, long beginTime, long endTime, int pageNo) {
        List<EquipStatus> statusList = getEquipData(name, beginTime, endTime);
        List<EquipStatus> valueList = new ArrayList<>();
        for (int i = pageNo * 15; i < (pageNo + 1) * 15; i++) {
            valueList.add(statusList.get(i));
        }
        return valueList;
    }

    @Override
    public List<EquipDetailData> getDetailData(long id, String type, long beginTime, long endTime) {
        if (id < 0) return null;
        if (beginTime == 0 || beginTime > endTime) return null;
        Sensor sensor = sensorMapper.selectById(id);

        List<EquipDetailData> detailDataList = new ArrayList<>();

        switch (sensor.getDepart()) {
            case "water_level":
                QueryWrapper<WaterLevel> waterLevelQueryWrapper1 = new QueryWrapper<>();
                waterLevelQueryWrapper1.in("topic", sensor.getTopic()).between("time", beginTime, endTime);
                if (type == "noLoad") waterLevelQueryWrapper1.le(sensor.getVal_name(), sensor.getNoLoadValue());
                else if (type == "general")
                    waterLevelQueryWrapper1.between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                else waterLevelQueryWrapper1.ge(sensor.getVal_name(), sensor.getOverLoadValue());
                List<WaterLevel> waterLevelList = waterLevelMapper.selectList(waterLevelQueryWrapper1);
                for (WaterLevel waterLevel : waterLevelList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(waterLevel.getTime());
                    data.setValue(waterLevel.getValue());
                    detailDataList.add(data);
                }
                break;
            case "tilt":
                QueryWrapper<Tilt> tiltQueryWrapper1 = new QueryWrapper<>();
                tiltQueryWrapper1.in("topic", sensor.getTopic()).between("time", beginTime, endTime);
                if (type == "noLoad") tiltQueryWrapper1.le(sensor.getVal_name(), sensor.getNoLoadValue());
                else if (type == "general")
                    tiltQueryWrapper1.between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                else tiltQueryWrapper1.ge(sensor.getVal_name(), sensor.getOverLoadValue());
                List<Tilt> tiltList = tiltMapper.selectList(tiltQueryWrapper1);
                for (Tilt tilt : tiltList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(tilt.getTime());
                    if (sensor.getVal_name() == "value1X") {
                        data.setValue(tilt.getVal1X());
                    } else if (sensor.getVal_name() == "value1Y") {
                        data.setValue(tilt.getVal1Y());
                    } else if (sensor.getVal_name() == "value2X") {
                        data.setValue(tilt.getVal2X());
                    } else if (sensor.getVal_name() == "value2Y") {
                        data.setValue(tilt.getVal2Y());
                    }
                    detailDataList.add(data);
                }

                break;
            case "vibration":
                QueryWrapper<Vibration> vibrationQueryWrapper1 = new QueryWrapper<>();
                vibrationQueryWrapper1.in("topic", sensor.getTopic()).between("time", beginTime, endTime);
                if (type == "noLoad") vibrationQueryWrapper1.le(sensor.getVal_name(), sensor.getNoLoadValue());
                else if (type == "general")
                    vibrationQueryWrapper1.between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                else vibrationQueryWrapper1.ge(sensor.getVal_name(), sensor.getOverLoadValue());
                List<Vibration> vibrationList = vibrationMapper.selectList(vibrationQueryWrapper1);
                for (Vibration vibration : vibrationList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(vibration.getTime());
                    if (sensor.getVal_name() == "valueH") {
                        data.setValue(vibration.getValueH());
                    } else {
                        data.setValue(vibration.getValueV());
                    }
                    detailDataList.add(data);
                }
                break;
            case "angle":
                QueryWrapper<Angle> angleQueryWrapper1 = new QueryWrapper<>();
                angleQueryWrapper1.in("topic", sensor.getTopic()).between("time", beginTime, endTime);
                if (type == "noLoad") angleQueryWrapper1.le(sensor.getVal_name(), sensor.getNoLoadValue());
                else if (type == "general")
                    angleQueryWrapper1.between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                else angleQueryWrapper1.ge(sensor.getVal_name(), sensor.getOverLoadValue());
                List<Angle> angleList = angleMapper.selectList(angleQueryWrapper1);
                for (Angle angle : angleList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(angle.getTime());
                    data.setValue(angle.getValue());
                    detailDataList.add(data);
                }

                break;
            case "weatherGeneral":
                QueryWrapper<WeatherGeneral> weatherGeneralQueryWrapper1 = new QueryWrapper<>();
                weatherGeneralQueryWrapper1.in("topic", sensor.getTopic()).between("time", beginTime, endTime);
                if (type == "noLoad") weatherGeneralQueryWrapper1.le(sensor.getVal_name(), sensor.getNoLoadValue());
                else if (type == "general")
                    weatherGeneralQueryWrapper1.between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                else weatherGeneralQueryWrapper1.ge(sensor.getVal_name(), sensor.getOverLoadValue());
                List<WeatherGeneral> weatherGeneralList = weatherGeneralMapper.selectList(weatherGeneralQueryWrapper1);
                for (WeatherGeneral general : weatherGeneralList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(general.getTime());
                    if (sensor.getVal_name() == "temperature") {
                        data.setValue(general.getTemperature());
                    } else if (sensor.getVal_name() == "humidity") {
                        data.setValue(general.getHumidity());
                    } else if (sensor.getVal_name() == "airPressure") {
                        data.setValue(general.getAirPressure());
                    } else if (sensor.getVal_name() == "windSpeed") {
                        data.setValue(general.getWindSpeed());
                    } else if (sensor.getVal_name() == "windDirection") {
                        data.setValue(general.getWindDirection());
                    }
                    detailDataList.add(data);
                }
                break;
            case "weatherRainfall":
                QueryWrapper<WeatherRainfall> weatherRainfallQueryWrapper = new QueryWrapper<>();
                weatherRainfallQueryWrapper.in("topic", sensor.getTopic()).between("time", beginTime, endTime);
                if (type == "noLoad") weatherRainfallQueryWrapper.le(sensor.getVal_name(), sensor.getNoLoadValue());
                else if (type == "general")
                    weatherRainfallQueryWrapper.between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                else weatherRainfallQueryWrapper.ge(sensor.getVal_name(), sensor.getOverLoadValue());
                List<WeatherRainfall> weatherRainfallList = weatherRainfallMapper.selectList(weatherRainfallQueryWrapper);
                for (WeatherRainfall rainfall : weatherRainfallList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(rainfall.getTime());
                    data.setValue(rainfall.getValue());
                    detailDataList.add(data);

                }
                break;
            case "weatherVisibility":
                QueryWrapper<WeatherVisibility> weatherVisibilityQueryWrapper1 = new QueryWrapper<>();
                weatherVisibilityQueryWrapper1.in("topic", sensor.getTopic()).between("time", beginTime, endTime);
                if (type == "noLoad") weatherVisibilityQueryWrapper1.le(sensor.getVal_name(), sensor.getNoLoadValue());
                else if (type == "general")
                    weatherVisibilityQueryWrapper1.between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                else weatherVisibilityQueryWrapper1.ge(sensor.getVal_name(), sensor.getOverLoadValue());
                List<WeatherVisibility> weatherVisibilityList = weatherVisibilityMapper.selectList(weatherVisibilityQueryWrapper1);
                for (WeatherVisibility weatherVisibility : weatherVisibilityList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(weatherVisibility.getTime());
                    data.setValue(weatherVisibility.getValue());
                    detailDataList.add(data);
                }
                break;
        }
        return detailDataList;

    }


    @Override
    public HSSFWorkbook outputRunningStatusXlsx(String name, long beginTime, long endTime, boolean isDetail) {
        String[][] value = getEquipStatus(name, beginTime, endTime);
        String[] header = {"ID", "传感器", "空载时间", "正常时间", "超载时间", "空载占比", "正常占比", "超载占比", "状态打分"};
        String title = name + "设备运行状态";
        HSSFWorkbook excel = new HSSFWorkbook();
        try {
            excel = ExcelUtil.getHSSFWorkbook(excel, title, header, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isDetail == true) {
            String[][] valueDetail = getEquipStatusDetail(name, beginTime, endTime);
            String[] headerDetail = {"time", "value"};
            String titleDetail = name + "详细数据";
        }
        return excel;
    }

    @Override
    public XWPFDocument outputRunningStatusDoc(String name, long beginTime, long endTime, boolean isDetail) {
        String[][] value = getEquipStatus(name, beginTime, endTime);
        String[] header = {"ID", "传感器", "空载时间", "正常时间", "超载时间", "空载占比", "正常占比", "超载占比", "状态大分"};
        String title = name + "设备运行状态";
        XWPFDocument word = null;
        try {
            word = WordUtil.outWord(title, header, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return word;
    }


    //设备运行状态数据转化为二维数组
    public String[][] getEquipStatus(String name, long beginTime, long endTime) {
        List<EquipStatus> statusList = getEquipData(name, beginTime, endTime);
        String[][] value = new String[statusList.size()][9];
        int index = 0;
        for (EquipStatus status : statusList) {
            value[index][0] = status.getId().toString();
            value[index][1] = status.getName();
            value[index][2] = status.getNoLoadTime().toString();
            value[index][3] = status.getGeneralTime().toString();
            value[index][4] = status.getOverLoadTime().toString();
            value[index][5] = status.getNoLoadPer().toString();
            value[index][6] = status.getGeneralPer().toString();
            value[index][7] = status.getOverLoadPer().toString();
            value[index][8] = status.getStatus().toString();
            index++;
        }
        return value;
    }

    //获取传感器数据
    public List<EquipStatus> getEquipData(String name, long beginTime, long endTime) {
        if (name == null || name.equals("")) return null;
        if (beginTime == 0 || beginTime > endTime) return null;

        // 获取对应传感器的topic
        QueryWrapper<Sensor> sensorQueryWrapper = new QueryWrapper<>();
        sensorQueryWrapper.select("depart", name);
        List<Sensor> sensorList = sensorMapper.selectList(sensorQueryWrapper);
        List<EquipStatus> statusList = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            EquipStatus equipStatus = new EquipStatus();
            equipStatus.setName(sensor.getName());
            switch (name) {
                case "water_level":
                    QueryWrapper<WaterLevel> waterLevelQueryWrapper1 = new QueryWrapper<>();
                    waterLevelQueryWrapper1.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).le(sensor.getVal_name(), sensor.getNoLoadValue());
                    List<WaterLevel> waterLevelList = waterLevelMapper.selectList(waterLevelQueryWrapper1);
                    equipStatus.setNoLoadTime(waterLevelList.size());

                    QueryWrapper<WaterLevel> waterLevelQueryWrapper2 = new QueryWrapper<>();
                    waterLevelQueryWrapper2.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                    waterLevelList = waterLevelMapper.selectList(waterLevelQueryWrapper2);
                    equipStatus.setGeneralTime(waterLevelList.size());

                    QueryWrapper<WaterLevel> waterLevelQueryWrapper3 = new QueryWrapper<>();
                    waterLevelQueryWrapper3.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).ge(sensor.getVal_name(), sensor.getOverLoadValue());
                    waterLevelList = waterLevelMapper.selectList(waterLevelQueryWrapper3);
                    equipStatus.setOverLoadTime(waterLevelList.size());

                    break;
                case "tilt":
                    QueryWrapper<Tilt> tiltQueryWrapper1 = new QueryWrapper<>();
                    tiltQueryWrapper1.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).le(sensor.getVal_name(), sensor.getNoLoadValue());
                    List<Tilt> tiltList = tiltMapper.selectList(tiltQueryWrapper1);
                    equipStatus.setNoLoadTime(tiltList.size());

                    QueryWrapper<Tilt> tiltQueryWrapper2 = new QueryWrapper<>();
                    tiltQueryWrapper2.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                    tiltList = tiltMapper.selectList(tiltQueryWrapper2);
                    equipStatus.setGeneralTime(tiltList.size());

                    QueryWrapper<Tilt> tiltQueryWrapper3 = new QueryWrapper<>();
                    tiltQueryWrapper3.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).ge(sensor.getVal_name(), sensor.getOverLoadValue());
                    tiltList = tiltMapper.selectList(tiltQueryWrapper3);
                    equipStatus.setOverLoadTime(tiltList.size());
                    break;
                case "vibration":
                    QueryWrapper<Vibration> vibrationQueryWrapper1 = new QueryWrapper<>();
                    vibrationQueryWrapper1.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).le(sensor.getVal_name(), sensor.getNoLoadValue());
                    List<Vibration> vibrationList = vibrationMapper.selectList(vibrationQueryWrapper1);
                    equipStatus.setNoLoadTime(vibrationList.size());


                    QueryWrapper<Vibration> vibrationQueryWrapper2 = new QueryWrapper<>();
                    vibrationQueryWrapper2.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                    vibrationList = vibrationMapper.selectList(vibrationQueryWrapper2);
                    equipStatus.setGeneralTime(vibrationList.size());

                    QueryWrapper<Vibration> vibrationQueryWrapper3 = new QueryWrapper<>();
                    vibrationQueryWrapper3.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).ge(sensor.getVal_name(), sensor.getOverLoadValue());
                    vibrationList = vibrationMapper.selectList(vibrationQueryWrapper3);
                    equipStatus.setOverLoadTime(vibrationList.size());
                    break;
                case "angle":
                    QueryWrapper<Angle> angleQueryWrapper1 = new QueryWrapper<>();
                    angleQueryWrapper1.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).le(sensor.getVal_name(), sensor.getNoLoadValue());
                    List<Angle> angleList = angleMapper.selectList(angleQueryWrapper1);
                    equipStatus.setNoLoadTime(angleList.size());

                    QueryWrapper<Angle> angleQueryWrapper2 = new QueryWrapper<>();
                    angleQueryWrapper2.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                    angleList = angleMapper.selectList(angleQueryWrapper2);
                    equipStatus.setGeneralTime(angleList.size());

                    QueryWrapper<Angle> angleQueryWrapper3 = new QueryWrapper<>();
                    angleQueryWrapper3.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).ge(sensor.getVal_name(), sensor.getOverLoadValue());
                    angleList = angleMapper.selectList(angleQueryWrapper3);
                    equipStatus.setOverLoadTime(angleList.size());

                    break;
                case "weatherGeneral":
                    QueryWrapper<WeatherGeneral> weatherGeneralQueryWrapper1 = new QueryWrapper<>();
                    weatherGeneralQueryWrapper1.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).le(sensor.getVal_name(), sensor.getNoLoadValue());
                    List<WeatherGeneral> weatherGeneralList = weatherGeneralMapper.selectList(weatherGeneralQueryWrapper1);
                    equipStatus.setNoLoadTime(weatherGeneralList.size());

                    QueryWrapper<WeatherGeneral> weatherGeneralQueryWrapper2 = new QueryWrapper<>();
                    weatherGeneralQueryWrapper2.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                    weatherGeneralList = weatherGeneralMapper.selectList(weatherGeneralQueryWrapper2);
                    equipStatus.setGeneralTime(weatherGeneralList.size());

                    QueryWrapper<WeatherGeneral> weatherGeneralQueryWrapper3 = new QueryWrapper<>();
                    weatherGeneralQueryWrapper3.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).ge(sensor.getVal_name(), sensor.getOverLoadValue());
                    weatherGeneralList = weatherGeneralMapper.selectList(weatherGeneralQueryWrapper3);
                    equipStatus.setOverLoadTime(weatherGeneralList.size());
                    break;
                case " weatherRainfall":
                    QueryWrapper<WeatherRainfall> weatherRainfallQueryWrapper = new QueryWrapper<>();
                    weatherRainfallQueryWrapper.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).le(sensor.getVal_name(), sensor.getNoLoadValue());
                    List<WeatherRainfall> weatherRainfallList = weatherRainfallMapper.selectList(weatherRainfallQueryWrapper);
                    equipStatus.setNoLoadTime(weatherRainfallList.size());

                    QueryWrapper<WeatherRainfall> weatherRainfallQueryWrapper2 = new QueryWrapper<>();
                    weatherRainfallQueryWrapper2.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                    weatherRainfallList = weatherRainfallMapper.selectList(weatherRainfallQueryWrapper2);
                    equipStatus.setGeneralTime(weatherRainfallList.size());

                    QueryWrapper<WeatherRainfall> weatherRainfallQueryWrapper3 = new QueryWrapper<>();
                    weatherRainfallQueryWrapper3.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).ge(sensor.getVal_name(), sensor.getOverLoadValue());
                    weatherRainfallList = weatherRainfallMapper.selectList(weatherRainfallQueryWrapper3);
                    equipStatus.setNoLoadTime(weatherRainfallList.size());
                    break;
                case "weatherVisibility":
                    QueryWrapper<WeatherVisibility> weatherVisibilityQueryWrapper1 = new QueryWrapper<>();
                    weatherVisibilityQueryWrapper1.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).le(sensor.getVal_name(), sensor.getNoLoadValue());
                    List<WeatherVisibility> weatherVisibilityList = weatherVisibilityMapper.selectList(weatherVisibilityQueryWrapper1);
                    equipStatus.setNoLoadTime(weatherVisibilityList.size());

                    QueryWrapper<WeatherVisibility> weatherVisibilityQueryWrapper2 = new QueryWrapper<>();
                    weatherVisibilityQueryWrapper2.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).between(sensor.getVal_name(), sensor.getNoLoadValue(), sensor.getOverLoadValue());
                    weatherVisibilityList = weatherVisibilityMapper.selectList(weatherVisibilityQueryWrapper2);
                    equipStatus.setGeneralTime(weatherVisibilityList.size());

                    QueryWrapper<WeatherVisibility> weatherVisibilityQueryWrapper3 = new QueryWrapper<>();
                    weatherVisibilityQueryWrapper3.eq("topic", sensor.getTopic()).between("time", beginTime, endTime).ge(sensor.getVal_name(), sensor.getOverLoadValue());
                    weatherVisibilityList = weatherVisibilityMapper.selectList(weatherVisibilityQueryWrapper3);
                    equipStatus.setOverLoadTime(weatherVisibilityList.size());

                    break;
            }
            int noLoad = equipStatus.getNoLoadTime();
            int gengeral = equipStatus.getGeneralTime();
            int overLoad = equipStatus.getOverLoadTime();
            int sum = noLoad + gengeral + overLoad;
            float per = noLoad / sum;
            equipStatus.setNoLoadPer(per);
            per = gengeral / sum;
            equipStatus.setGeneralPer(per);
            per = overLoad / sum;
            equipStatus.setOverLoadPer(per);

            statusList.add(equipStatus);
        }

        return statusList;
    }

    public String[][] getEquipStatusDetail(String name, long beginTime, long endTime) {
        if (name == null || name.equals("")) return null;
        if (beginTime == 0 || beginTime > endTime) return null;

        // 获取对应传感器的topic
        QueryWrapper<Sensor> sensorQueryWrapper = new QueryWrapper<>();
        sensorQueryWrapper.select("depart", name);
        List<Sensor> sensorList = sensorMapper.selectList(sensorQueryWrapper);
        List<String> topicList = new ArrayList<>();
        for (Sensor sensor : sensorList) {
            topicList.add(sensor.getTopic());
        }
        List<EquipDetailData> detailDataList = new ArrayList<>();

        switch (name) {
            case "water_level":
                QueryWrapper<WaterLevel> waterLevelQueryWrapper1 = new QueryWrapper<>();
                waterLevelQueryWrapper1.in("topic", topicList).between("time", beginTime, endTime).select(sensorList.get(0).getVal_name());
                List<WaterLevel> waterLevelList = waterLevelMapper.selectList(waterLevelQueryWrapper1);
                for (WaterLevel waterLevel : waterLevelList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(waterLevel.getTime());
                    data.setValue(waterLevel.getValue());
                    detailDataList.add(data);
                }

                break;
            case "tilt":
                QueryWrapper<Tilt> tiltQueryWrapper1 = new QueryWrapper<>();
                tiltQueryWrapper1.in("topic", topicList).between("time", beginTime, endTime).select(sensorList.get(0).getVal_name());
                List<Tilt> tiltList = tiltMapper.selectList(tiltQueryWrapper1);
                for (Tilt tilt : tiltList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(tilt.getTime());
                    if (sensorList.get(0).getVal_name() == "value1X") {
                        data.setValue(tilt.getVal1X());
                    } else if (sensorList.get(0).getVal_name() == "value1Y") {
                        data.setValue(tilt.getVal1Y());
                    } else if (sensorList.get(0).getVal_name() == "value2X") {
                        data.setValue(tilt.getVal2X());
                    } else if (sensorList.get(0).getVal_name() == "value2Y") {
                        data.setValue(tilt.getVal2Y());
                    }
                    detailDataList.add(data);
                }

                break;
            case "vibration":
                QueryWrapper<Vibration> vibrationQueryWrapper1 = new QueryWrapper<>();
                vibrationQueryWrapper1.in("topic", topicList).between("time", beginTime, endTime).select(sensorList.get(0).getVal_name());
                List<Vibration> vibrationList = vibrationMapper.selectList(vibrationQueryWrapper1);
                for (Vibration vibration : vibrationList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(vibration.getTime());
                    if (sensorList.get(0).getVal_name() == "valueH") {
                        data.setValue(vibration.getValueH());
                    } else {
                        data.setValue(vibration.getValueV());
                    }
                    detailDataList.add(data);
                }
                break;
            case "angle":
                QueryWrapper<Angle> angleQueryWrapper1 = new QueryWrapper<>();
                angleQueryWrapper1.in("topic", topicList).between("time", beginTime, endTime).select(sensorList.get(0).getVal_name());
                List<Angle> angleList = angleMapper.selectList(angleQueryWrapper1);
                for (Angle angle : angleList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(angle.getTime());
                    data.setValue(angle.getValue());
                    detailDataList.add(data);
                }

                break;
            case "weatherGeneral":
                QueryWrapper<WeatherGeneral> weatherGeneralQueryWrapper1 = new QueryWrapper<>();
                weatherGeneralQueryWrapper1.in("topic", topicList).between("time", beginTime, endTime).select(sensorList.get(0).getVal_name());
                List<WeatherGeneral> weatherGeneralList = weatherGeneralMapper.selectList(weatherGeneralQueryWrapper1);
                for (WeatherGeneral general : weatherGeneralList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(general.getTime());
                    if (sensorList.get(0).getVal_name() == "temperature") {
                        data.setValue(general.getTemperature());
                    } else if (sensorList.get(0).getVal_name() == "humidity") {
                        data.setValue(general.getHumidity());
                    } else if (sensorList.get(0).getVal_name() == "airPressure") {
                        data.setValue(general.getAirPressure());
                    } else if (sensorList.get(0).getVal_name() == "windSpeed") {
                        data.setValue(general.getWindSpeed());
                    } else if (sensorList.get(0).getVal_name() == "windDirection") {
                        data.setValue(general.getWindDirection());
                    }


                    detailDataList.add(data);

                }
                break;
            case " weatherRainfall":
                QueryWrapper<WeatherRainfall> weatherRainfallQueryWrapper = new QueryWrapper<>();
                weatherRainfallQueryWrapper.in("topic", topicList).between("time", beginTime, endTime).select(sensorList.get(0).getVal_name());
                List<WeatherRainfall> weatherRainfallList = weatherRainfallMapper.selectList(weatherRainfallQueryWrapper);
                for (WeatherRainfall rainfall : weatherRainfallList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(rainfall.getTime());
                    data.setValue(rainfall.getValue());
                    detailDataList.add(data);

                }
                break;
            case "weatherVisibility":
                QueryWrapper<WeatherVisibility> weatherVisibilityQueryWrapper1 = new QueryWrapper<>();
                weatherVisibilityQueryWrapper1.in("topic", topicList).between("time", beginTime, endTime).select(sensorList.get(0).getVal_name());
                List<WeatherVisibility> weatherVisibilityList = weatherVisibilityMapper.selectList(weatherVisibilityQueryWrapper1);
                for (WeatherVisibility weatherVisibility : weatherVisibilityList) {
                    EquipDetailData data = new EquipDetailData();
                    data.setTime(weatherVisibility.getTime());
                    data.setValue(weatherVisibility.getValue());
                    detailDataList.add(data);
                }
                break;
        }

        String[][] value = new String[detailDataList.size()][2];
        int index = 0;
        for (EquipDetailData detailData : detailDataList) {
            value[index][0] = detailData.getTime().toString();
            value[index][1] = detailData.getValue().toString();
        }
        return value;
    }


    public LocalDateTime longToDateTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    public long getTimestampOfDateTime(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.toEpochMilli();
    }

}
