package com.cit.its.shipping.front.util;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeUtil {

    public static LocalDateTime strToDateLong(String year, String month) {

        String strDate = year + month + "010000";
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyyMMddHHmm").parse(strDate);//先按照原格式转换为时间
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String str = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);//再将时间转换为对应格式字符串
        LocalDateTime beginDateTime = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return beginDateTime;

    }

    public static Map<String, LocalDateTime> getQueryDateMap(String year, String month){

        Map<String,LocalDateTime> map = new HashMap<>();
        String month2 = "0" + new BigInteger(month).add(new BigInteger("1")).toString();
        LocalDateTime beginDateTime = strToDateLong(year,month);
        LocalDateTime endDateTime = strToDateLong(year,month2);
        map.put("beginDateTime", beginDateTime);
        map.put("endDateTime", endDateTime);
        return map;
    }
}
