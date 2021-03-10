package com.lhz.staservice.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: lhz
 * @Description: 日期工具类
 */
public class DateUtil {

    private static final String dateFormat = "yyyy-MM-dd";

    /**
     * @author: lhz
     * @Description: 格式化日期
     */
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);

    }

    /**
     * @author: lhz
     * @Description: 日期增加天数
     */
    public static Date addDays(Date date, int amount) {
        Calendar now =Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE,now.get(Calendar.DATE)+amount);
        return now.getTime();
    }
}
