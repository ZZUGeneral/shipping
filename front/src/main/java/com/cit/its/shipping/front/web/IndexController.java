package com.cit.its.shipping.front.web;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cit.its.shipping.front.dto.*;
import com.cit.its.shipping.front.entity.*;
import com.cit.its.shipping.front.enums.ClientTypeEnum;
import com.cit.its.shipping.front.service.*;
import com.cit.its.shipping.front.vo.DevicesStateVo;
import com.cit.its.shipping.front.vo.PageVo;
import com.cit.its.shipping.front.vo.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class IndexController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private WaterLevelService waterLevelService;
    @Autowired
    private TiltService tiltService;
    @Autowired
    private VibrationService vibrationService;
    @Autowired
    private AngleService angleService;
    @Autowired
    private WeatherGeneralService weatherGeneralService;
    @Autowired
    private WeatherRainfallService weatherRainfallService;
    @Autowired
    private WeatherVisibilityService weatherVisibilityService;
    @Autowired
    private TriggerService triggerService;
    @Autowired
    private EventService eventService;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index");
    }

    @GetMapping("/login")
    public ModelAndView login() {

        return new ModelAndView("login");
    }

    @GetMapping("/devices")
    public ModelAndView devices(Model model) {
        ClientTypeEnum[] values = ClientTypeEnum.values();
        List<ClientTypeEnum> typeList = Arrays.stream(values)
                .filter(v -> v.getCode() > 0)
                .collect(Collectors.toList());
        model.addAttribute("typeList", typeList);
        return new ModelAndView("devices");

    }

    @GetMapping("/history")
    public ModelAndView history() {
        return new ModelAndView("history");
    }

    @GetMapping("/trigger")
    public ModelAndView trigger() {
        return new ModelAndView("trigger");
    }

    @GetMapping("/event")
    public ModelAndView event() {
        return new ModelAndView("event");
    }

    @PostMapping("/devices/pageData")
    public Result devicesPageData(Client client, Integer typeValue,
                                  @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        if (typeValue != null) {
            client.setType(ClientTypeEnum.getByCode(typeValue));
        }
        IPage<Client> clientIPage = clientService.clientPageList(client, page, size);
        PageVo<Client> pageVo = new PageVo<Client>(page, size, clientIPage.getTotal(), clientIPage.getRecords());
        return Result.success(pageVo);
    }


    @GetMapping("/devices/state")
    public Result devicesStatus() {
        List<Client> sensorList = clientService.sensorList();
        List<DevicesStateVo> voList = CollUtil.newArrayList();
        sensorList.stream()
                .collect(Collectors.groupingBy(Client::getType))
                .forEach((type, clients) -> {
                    int online = clients.stream()
                            .filter(c -> c.getState() == 1)
                            .mapToInt(c -> 1).sum();
                    voList.add(new DevicesStateVo(type.getCode(), online, clients.size()));
                });
        int totalOnline = sensorList.stream()
                .filter(s -> s.getState() == 1)
                .mapToInt(s -> 1)
                .sum();
        voList.add(new DevicesStateVo(0, totalOnline, sensorList.size()));
        return Result.success(voList);
    }

    @ApiOperation(value = "获取水位历史数据", notes = "获取水位历史数据")
    @PostMapping("history/waterLevel")
    public Result historyWaterLevelData(@RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        List<WaterLevel> waterLevelList = waterLevelService.waterLevelHistory(null, beginDateTime, endDateTime);
        //waterLevelList = waterLevelList.subList(0, 1000);
        Map<String, List<WaterLevel>> waterLevelMap = waterLevelList.stream().collect(Collectors.groupingBy(WaterLevel::getTopic));
        WaterLevelStatisticsDto waterLevelStatisticsDto = waterLevelService.waterLevelStatistics(null, beginDateTime, endDateTime);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("waterLevel", waterLevelMap);
        dataMap.put("sta", waterLevelStatisticsDto);
        return Result.success(dataMap);
    }

//    @PostMapping("history/tilt")
//    public Result historyTiltData(@RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
//        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        List<Tilt> tiltList = tiltService.tiltHistory(null, beginDateTime, endDateTime);
////        tiltList = tiltList.subList(0, 1000);
//        Map<String, List<Tilt>> tiltMap = tiltList.stream().collect(Collectors.groupingBy(Tilt::getTopic));
//        TiltStatisticsDto x1TiltStatisticsDto = tiltService.x1TiltStatistics(null, beginDateTime, endDateTime);
//        TiltStatisticsDto y1TiltStatisticsDto = tiltService.y1TiltStatistics(null, beginDateTime, endDateTime);
//        TiltStatisticsDto x2TiltStatisticsDto = tiltService.x2TiltStatistics(null, beginDateTime, endDateTime);
//        TiltStatisticsDto y2TiltStatisticsDto = tiltService.y2TiltStatistics(null, beginDateTime, endDateTime);
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("tilt", tiltMap);
//        dataMap.put("sta_x1", x1TiltStatisticsDto);
//        dataMap.put("sta_y1", y1TiltStatisticsDto);
//        dataMap.put("sta_x2", x2TiltStatisticsDto);
//        dataMap.put("sta_y2", y2TiltStatisticsDto);
//        return Result.success(dataMap);
//    }
//
//    @PostMapping("history/vibration")
//    public Result historyVibrationData(@RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
//        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        List<Vibration> vibrationList = vibrationService.vibrationHistory(null, beginDateTime, endDateTime);
////        vibrationList = vibrationList.subList(0, 1000);
//        System.out.println(vibrationList.toString());
//        Map<String, List<Vibration>> vibrationMap = vibrationList.stream().collect(Collectors.groupingBy(Vibration::getTopic));
//        VibrationStatisticsDto vVibrationStatisticsDto = vibrationService.vVibrationStatistics(null, beginDateTime, endDateTime);
//        VibrationStatisticsDto hVibrationStatisticsDto = vibrationService.hVibrationStatistics(null, beginDateTime, endDateTime);
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("vibration", vibrationMap);
//        dataMap.put("sta_v", vVibrationStatisticsDto);
//        dataMap.put("sta_h", hVibrationStatisticsDto);
//        return Result.success(dataMap);
//    }
//
//    @PostMapping("history/angle")
//    public Result historyAngleData(@RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
//        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        List<Angle> angleList = angleService.angleHistory(null, beginDateTime, endDateTime);
////        angleList = angleList.subList(0, 1000);
//        Map<String, List<Angle>> angleMap = angleList.stream().collect(Collectors.groupingBy(Angle::getTopic));
//        AngleStatisticsDto angleStatisticsDto = angleService.angleStatistics(null, beginDateTime, endDateTime);
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("angle", angleMap);
//        dataMap.put("sta", angleStatisticsDto);
//        return Result.success(dataMap);
//    }
//
//    @PostMapping("history/weather_general")
//    public Result historyWeatherGeneralData(@RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
//        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        List<WeatherGeneral> weatherGeneralList = weatherGeneralService.weatherGeneralHistory(null, beginDateTime, endDateTime);
////        angleList = angleList.subList(0, 1000);
//        Map<String, List<WeatherGeneral>> weatherGeneralMap = weatherGeneralList.stream().collect(Collectors.groupingBy(WeatherGeneral::getTopic));
//        WeatherGeneralStatisticsDto temperatureStatisticsDto = weatherGeneralService.temperatureStatistics(null, beginDateTime, endDateTime);
//        WeatherGeneralStatisticsDto humidityStatisticsDto = weatherGeneralService.humidityStatistics(null, beginDateTime, endDateTime);
//        WeatherGeneralStatisticsDto airPressureStatisticsDto = weatherGeneralService.airPressureStatistics(null, beginDateTime, endDateTime);
//        WeatherGeneralStatisticsDto windSpeedStatisticsDto = weatherGeneralService.windSpeedStatistics(null, beginDateTime, endDateTime);
//        WeatherGeneralStatisticsDto windDirectionStatisticsDto = weatherGeneralService.windDirectionStatistics(null, beginDateTime, endDateTime);
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("weatherGeneral", weatherGeneralMap);
//        dataMap.put("sta_temperature", temperatureStatisticsDto);
//        dataMap.put("sta_humidity", humidityStatisticsDto);
//        dataMap.put("sta_airPressure", airPressureStatisticsDto);
//        dataMap.put("sta_windSpeed", windSpeedStatisticsDto);
//        dataMap.put("sta_windDirection", windDirectionStatisticsDto);
//        return Result.success(dataMap);
//    }
//
//    @PostMapping("history/weather_rainfall")
//    public Result historyWeatherRainfallData(@RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
//        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        List<WeatherRainfall> weatherRainfallList = weatherRainfallService.weatherRainfallHistory(null, beginDateTime, endDateTime);
////        angleList = angleList.subList(0, 1000);
//        Map<String, List<WeatherRainfall>> weatherRainfallMap = weatherRainfallList.stream().collect(Collectors.groupingBy(WeatherRainfall::getTopic));
//        WeatherRainfallStatisticsDto weatherRainfallStatisticsDto = weatherRainfallService.weatherRainfallStatistics(null, beginDateTime, endDateTime);
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("weatherRainfall", weatherRainfallMap);
//        dataMap.put("sta", weatherRainfallStatisticsDto);
//        return Result.success(dataMap);
//    }
//
//    @PostMapping("history/weather_visibility")
//    public Result historyWeatherVisibilityData(@RequestParam(value = "beginDateStr", required = false) String beginDateStr, @RequestParam(value = "endDateStr", required = false) String endDateStr) {
//        LocalDateTime beginDateTime = LocalDateTime.parse(beginDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        LocalDateTime endDateTime = LocalDateTime.parse(endDateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//        List<WeatherVisibility> weatherVisibilityList = weatherVisibilityService.weatherVisibilityHistory(null, beginDateTime, endDateTime);
////        angleList = angleList.subList(0, 1000);
//        Map<String, List<WeatherVisibility>> weatherVisibilityMap = weatherVisibilityList.stream().collect(Collectors.groupingBy(WeatherVisibility::getTopic));
//        WeatherVisibilityStatisticsDto weatherVisibilityStatisticsDto = weatherVisibilityService.weatherVisibilityStatistics(null, beginDateTime, endDateTime);
//        Map<String, Object> dataMap = new HashMap<>();
//        dataMap.put("weatherVisibility", weatherVisibilityMap);
//        dataMap.put("sta", weatherVisibilityStatisticsDto);
//        return Result.success(dataMap);
//    }

}
