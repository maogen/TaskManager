package com.tgram.android.task.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String defaultFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 将时间格式化字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String getDateByFormat(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * 根据格式，获取时间字符串
     *
     * @param format
     * @return
     */
    public static String getDateByFormat(String format) {
        if (StringUtil.isEmpty(format)) {
            format = defaultFormat;
        }
        return getDateByFormat(new Date(), format);
    }

    /**
     * 判断两个时间的间隔
     *
     * @param formatDate1 格式化后的时间，时间格式是yyyy-MM-dd HH:mm:ss
     * @param formatDate2 格式化后的时间，时间格式是yyyy-MM-dd HH:mm:ss
     * @return 间隔的秒数
     */
    public static long getDateSpace(String formatDate1, String formatDate2) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(defaultFormat);
            Date date1 = dateFormat.parse(formatDate1);
            Date date2 = dateFormat.parse(formatDate2);
            return (date1.getTime() - date2.getTime()) / 1000;
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
