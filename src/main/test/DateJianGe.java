import com.dousnl.utils.date.DateUtil;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2020/10/22
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2020/10/22       hanliangliang     新增              1001
 ********************************************************************/
public class DateJianGe {
    public static void main(String[] args) throws ParseException {
        Date start = DateUtil.parse("2019-10-22 08:29:38", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.parse("2012-02-27 08:29:38", "yyyy-MM-dd HH:mm:ss");
        // 要求：判断平年和闰年。
        //闰年：能被4整除但不能被100整除或者能被400整除
//        if(end%4==0&&end%100!=0||end%400==0){
//            System.out.println(year+"是闰年");
//        }
        int dateSpace = DateUtil.getDateSpace(start, end);
        System.out.println(dateSpace - 1);
        String datePoor = getDatePoor(end, start);
        //System.out.println((double)1462/367);
        System.out.println(datePoor);
        //1.平年，平年
        //2.平年，闰年
        //3.闰年，平年
        //4.闰年，闰年
        int nianNum = nianNum(start);
        System.out.println("点亮："+nianNum+"年");
        Date register=new Date();
        if (!isAfterNowYear(register)){
            return;
        }
        if (isPingNian(end) && isPingNian(start)) {
            if (isZhouNian(end,start)){
                System.out.println("周年");
            }
        } else if (isPingNian(start) && isRunNian(end)) {
            if (isZhouNian(end,start)){
                System.out.println("周年");
            }
        } else if (isRunNian(start) && isPingNian(end)) {
            if (is229(start,end)){
                System.out.println("周年");
            }
        }else if (isRunNian(start) && isRunNian(end)) {
            if (isZhouNian(end,start)){
                System.out.println("周年");
            }
        }

    }

    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    public static long getDatePoorDay(Date endDate, Date startDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        return day;
    }

    public static boolean is365(Date endDate, Date startDate) {
        long datePoorDay = getDatePoorDay(endDate, startDate);
        return datePoorDay % 365 == 0 ? true : false;
    }

    public static boolean is366(Date endDate, Date startDate) {
        long datePoorDay = getDatePoorDay(endDate, startDate);
        return datePoorDay % 366 == 0 ? true : false;
    }

    public static boolean isAfterNowYear(Date register) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(register);
        int year = calendar.get(Calendar.YEAR);

        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        int yearNow = calendarNow.get(Calendar.YEAR);
        if (yearNow > year) {
            return true;
        }
        return false;
    }

    public static boolean isPingNian(Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        int year = calendar.get(Calendar.YEAR);
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            //System.out.println(year + "是闰年");
            return false;
        }
        return true;
    }

    public static boolean isRunNian(Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        int year = calendar.get(Calendar.YEAR);
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            //System.out.println(year + "是闰年");
            return true;
        }
        return false;
    }

    public static boolean isBefore229(Date endDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (month < 2) {
            return true;
        }
        if (month == 2) {
            if (day < 29) {
                return true;
            }
        }
        return false;
    }

    public static boolean is229(Date start,Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(end);
        int monthEnd = calendarEnd.get(Calendar.MONTH) + 1;
        int dayEnd = calendarEnd.get(Calendar.DAY_OF_MONTH);
        if (month == 2 && day==29) {
            if (monthEnd==3 && dayEnd==1){
                return true;
            }
        }
        return isZhouNian(end,start);
    }

    public static boolean isZhouNian(Date endDate, Date startDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(startDate);
        int monthStart = calendarStart.get(Calendar.MONTH) + 1;
        int dayStart = calendarStart.get(Calendar.DAY_OF_MONTH);
        if (month==monthStart && day==dayStart){
            return true;
        }
        return false;
    }

    public static int nianNum(Date register) {
        if (!isAfterNowYear(register)) {
            return 0;
        }
        Calendar calendarNow = Calendar.getInstance();
        calendarNow.setTime(new Date());
        int yearNow = calendarNow.get(Calendar.YEAR);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(register);
        int year = calendar.get(Calendar.YEAR);
        calendar.set(Calendar.YEAR, yearNow);

        Integer now = Integer.parseInt(DateUtil.dateToString(calendarNow.getTime(), "yyyyMMdd"));
        Integer reg = Integer.parseInt(DateUtil.dateToString(calendar.getTime(), "yyyyMMdd"));

        System.out.println(now);
        System.out.println(reg);
        if (now >= reg) {
            return (yearNow - year) > 3 ? 3 : (yearNow - year);
        }
        return (yearNow - year) > 0 ? (yearNow - year - 1) : 0;
    }
}
