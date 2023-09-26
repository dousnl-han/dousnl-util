package com.dousnl.utils;

import com.dousnl.utils.date.DateUtil;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2023/4/4
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2023/4/4       hanliangliang     新增              1001
 ********************************************************************/
public class TimeHelper {
    /**
     * 获取下一个分钟值以0或者5结尾的时间点（单位：毫秒）
     * @return
     */
    public static long getNextMillisEndWithMinute0or5(Date baseTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseTime);
        int minute = calendar.get(Calendar.MINUTE);
        if (minute < 55) {
            int add = minute%10 < 5? 5 - minute%10 : 10 - minute%10;
            calendar.add(Calendar.MINUTE,add);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar.getTime().getTime();
        }
        //当前时间+1小时
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date endTime = DateUtils.addHours(calendar.getTime(), 1);
        return endTime.getTime();
    }

    public static String getNextFiveTime() throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.parse("2023-04-05 23:54:14 000"));
        calendar.add(Calendar.MINUTE,(10-calendar.get(Calendar.MINUTE)%10));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        String dateToString = DateUtil.dateToString(new Date(calendar.getTime().getTime()));
        System.out.println(dateToString);
        System.out.println(23%10);
        return dateToString;
    }

    public static void main(String[] args) throws ParseException {
        Date parse = DateUtil.parse("2023-04-04 23:19:14 000");
        long nextMillisEndWithMinute0or5 = getNextMillisEndWithMinute0or5(parse);
        String dateToString = DateUtil.dateToString(new Date(nextMillisEndWithMinute0or5));
        System.out.println(dateToString);

        getNextFiveTime();
    }
}
