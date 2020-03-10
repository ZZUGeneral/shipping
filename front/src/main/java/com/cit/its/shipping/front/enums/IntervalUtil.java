package com.cit.its.shipping.front.enums;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalUtil {

    public static List<List<Integer>> intervalSet(String leftInterval, String rightInterval, String count){

        int intervalMin = Integer.parseInt(leftInterval);
        int intervalMax = Integer.parseInt(rightInterval);
        int countInt = Integer.parseInt(count);
        int range = (intervalMax - intervalMin) / countInt == 0 ? 1 : (intervalMax - intervalMin) / countInt;
        List<List<Integer>> resultList = new ArrayList<>();
        int tmpStart = intervalMin, b1, b2;
        for(int i = 0;i < countInt; i++){
            b1 = tmpStart;
            b2 = range;
            resultList.add(Arrays.asList(b1, b1 + b2));
            tmpStart = b1 + b2;
        }
        if (tmpStart < intervalMax) {
            resultList.add(Arrays.asList(tmpStart, intervalMax));
        }

        return resultList;
    }
}
