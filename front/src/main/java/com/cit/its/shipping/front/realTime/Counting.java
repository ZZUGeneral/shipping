package com.cit.its.shipping.front.realTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *@Author: 黄贵生
 *@Description: 实时计算方法，计算后的数据写入map
 */
public class Counting {

    public static Map<String, Float> calculate(float max, float min, float avg, float sum, float varPop, ArrayList<Float> valList){

        Map<String, Float> map = new HashMap<String, Float>();
        max = (float)valList.get(0);
        min = (float)valList.get(0);
        avg = sum = varPop = 0;

        for (int i = 0; i < valList.size(); i++) {
            if (min > (Float)valList.get(i)) min = (float)valList.get(i);
            if (max < (Float)valList.get(i)) max = (float)valList.get(i);
            sum += valList.get(i);
        }

        avg = sum / valList.size();
        for (int i = 0; i < valList.size(); i++) {
            varPop += (valList.get(i) - avg) * (valList.get(i) - avg);
        }
        varPop = varPop / valList.size();
        System.out.println(max + "   " + "------最大最小值-----" + "   " + min);
        System.out.println(sum + "   " + "------总和平均值-----" + "   " + avg);
        System.out.println(varPop + "   " + "------方差-----");

        map.put("max", max);
        map.put("min", min);
        map.put("avg", avg);
        map.put("varPop", varPop);
        return map;
    }
}
