package com.cit.its.shipping.front.realTime;

import com.cit.its.shipping.front.entity.WaterLevel;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.cit.its.shipping.front.realTime.Counting.calculate;


@Data
public class WaterLevelRealTime {

    private ArrayList<Float> valList = new ArrayList<Float>();
    private float max;
    private float min;
    private float sum;
    private float avg;
    private float varPop;

    public String waterLevelProcess(WaterLevel waterLevel){
        Map<String, Float> map = new HashMap<String, Float>();
        String jsonStr = "";
        if(waterLevel != null){
            float jsonVal = waterLevel.getValue();
            long jsonTime = waterLevel.getTime();
            valList.add(jsonVal);
            map = calculate(max, min, avg, sum, varPop, valList);
            jsonStr = "{" + "\"" + "val" + "\"" + ":" + jsonVal + "," + "\"" + "time"
                    + "\"" + ":" + jsonTime + "," + "\"" + "valCounting" + "\"" + ":" +
                    "{" + "\"" + "max" + "\"" + ":" + map.get("max") + "," + "\"" + "min" + "\"" +
                    ":" + map.get("min") + "," + "\"" + "avg" + "\"" + ":" + map.get("avg")  + "," + "\"" + "varPop"
                    + "\"" + ":" + map.get("varPop") + "}" + "}";
            System.out.println(jsonStr);
        }
        return jsonStr;
    }
}