package com.cit.its.shipping.front.service.impl;

import com.cit.its.shipping.front.FrontApplicationTests;
import com.cit.its.shipping.front.dto.WaterLevelStatisticsDto;
import com.cit.its.shipping.front.entity.WaterLevel;
import com.cit.its.shipping.front.service.WaterLevelService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class WaterLevelServiceImplTest extends FrontApplicationTests {

    @Autowired
    private WaterLevelService service;

    @Test
    public void waterLevelHistory() {
        List<WaterLevel> waterLevels = service.waterLevelHistory("get/waterLevel/w1", null, null);
        System.out.println(waterLevels.size());
        Assert.assertNotNull(waterLevels);
    }
}