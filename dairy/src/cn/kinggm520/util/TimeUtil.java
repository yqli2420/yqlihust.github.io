package cn.kinggm520.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 作者: kinggm Email:731586355@qq.com
 * 时间:  2020-03-03 0:20
 */
public class TimeUtil {

    public static String getTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        return time;
    }

}
