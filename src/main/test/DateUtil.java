import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.Hours;
import org.joda.time.Interval;
import org.joda.time.LocalDate;
import org.joda.time.Minutes;
import org.joda.time.Months;
import org.joda.time.Seconds;
import org.joda.time.Years;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Description: TODO
 * Company    : 上海黄豆网络科技有限公司
 *
 * @author : hanliangliang
 * Date       : 2021/3/26
 * Modify     : 修改日期          修改人员        修改说明          JIRA编号
 * v1.0.0       2021/3/26       hanliangliang     新增              1001
 ********************************************************************/
public class DateUtil {
    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
    public static final String SLASH_DATE_FORMAT = "yyyy/MM/dd";
    public static final String NOT_SLASH_DATE_FORMAT = "yyyyMMdd";
    public static final String CN_DATE_FORMAT = "yyyy'年'MM'月'dd'日'";
    public static final String MONTH_DAY_YEAR_FORMAT = "M/d/yyyy";
    public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String SLASH_DATETIME_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String NOT_SLASH_DATETIME_FORMAT = "yyyyMMdd HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> SLASH_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> NOT_SLASH_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> CN_DATE_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> MONTH_DAY_YEAR_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("M/d/yyyy");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_DATETIME_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> SLASH_DATETIME_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> NOT_SLASH_DATETIME_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> DEFAULT_TIME_FORMATTER = new ThreadLocal<SimpleDateFormat>() {
        protected synchronized SimpleDateFormat initialValue() {
            return new SimpleDateFormat("HH:mm:ss");
        }
    };

    public DateUtil() {
    }

    public static final Date getCurrentDate() {
        return (new DateTime()).toDate();
    }

    public static final String getCurrentDefaultDateString() {
        return (new DateTime()).toString("yyyy-MM-dd");
    }

    public static final String getCurrentNotSlashDateString() {
        return (new DateTime()).toString("yyyyMMdd");
    }

    public static final String getCurrentDateTimeString() {
        return (new DateTime()).toString("yyyy-MM-dd HH:mm:ss");
    }

    public static final String getCurrentTimeString() {
        return (new DateTime()).toString("HH:mm:ss");
    }

    public static final Calendar getCurrentCalendar() {
        return Calendar.getInstance();
    }

    public static final Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static final Calendar getCalendar(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(date));
        return calendar;
    }

    public static final Calendar getCalendar(String date, String pattern) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(parseDate(date, pattern));
        return calendar;
    }

    public static final Date makeDate(int year, int month, int day) {
        return (new LocalDate(year, month, day)).toDate();
    }

    public static final Date makeDateTime(int year, int month, int day, int hour, int minute, int second) {
        return (new DateTime(year, month, day, hour, minute, second, 0)).toDate();
    }

    public static final Date parseDate(String date) {
        DateFormat[] formatters = new DateFormat[]{(DateFormat)DEFAULT_DATETIME_FORMATTER.get(), (DateFormat)SLASH_DATETIME_FORMATTER.get(), (DateFormat)NOT_SLASH_DATETIME_FORMATTER.get(), (DateFormat)DEFAULT_TIME_FORMATTER.get(), (DateFormat)DEFAULT_DATE_FORMATTER.get(), (DateFormat)SLASH_DATE_FORMATTER.get(), (DateFormat)NOT_SLASH_DATE_FORMATTER.get(), (DateFormat)CN_DATE_FORMATTER.get(), (DateFormat)MONTH_DAY_YEAR_FORMATTER.get()};
        DateFormat[] var2 = formatters;
        int var3 = formatters.length;
        int var4 = 0;

        while(var4 < var3) {
            DateFormat formatter = var2[var4];

            try {
                return formatter.parse(date);
            } catch (ParseException var7) {
                ++var4;
            }
        }

        return null;
    }

    public static final <T extends Date> T parseDate(String date, Class<T> clz) throws ParseException, InstantiationException, IllegalAccessException {
        T t = (T) clz.newInstance();
        t.setTime(parseDate(date).getTime());
        return t;
    }

    public static final Date parseDate(String date, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(date);
    }

    public static final String formatDate(Date date) {
        return (new DateTime(date)).toString("yyyy-MM-dd");
    }

    public static final String formatDateTime(Date date) {
        return (new DateTime(date)).toString("yyyy-MM-dd HH:mm:ss");
    }

    public static final String formatTime(Date date) {
        return (new DateTime(date)).toString("HH:mm:ss");
    }

    public static final String formatDate(Date date, String pattern) {
        return (new DateTime(date)).toString(pattern);
    }

    public static final int getYearsBetween(Date a, Date b) {
        return Years.yearsBetween(toJodaDateTime(a), toJodaDateTime(b)).getYears();
    }

    public static final int getYearsBetween(String a, String b) throws ParseException {
        return Years.yearsBetween(toJodaDateTime(a), toJodaDateTime(b)).getYears();
    }

    public static final int getMonthsBetween(Date a, Date b) {
        return Months.monthsBetween(toJodaDateTime(a), toJodaDateTime(b)).getMonths();
    }

    public static final int getMonthsBetween(String a, String b) throws ParseException {
        return Months.monthsBetween(toJodaDateTime(a), toJodaDateTime(b)).getMonths();
    }

    public static final int getDaysBetween(Date a, Date b) {
        return Days.daysBetween(toJodaDateTime(a), toJodaDateTime(b)).getDays();
    }

    public static final int getDaysBetween(String a, String b) throws ParseException {
        return Days.daysBetween(toJodaDateTime(a), toJodaDateTime(b)).getDays();
    }

    public static final int getHoursBetween(Date a, Date b) {
        return Hours.hoursBetween(toJodaDateTime(a), toJodaDateTime(b)).getHours();
    }

    public static final int getHoursBetween(String a, String b) throws ParseException {
        return Hours.hoursBetween(toJodaDateTime(a), toJodaDateTime(b)).getHours();
    }

    public static final int getMinutesBetween(Date a, Date b) {
        return Minutes.minutesBetween(toJodaDateTime(a), toJodaDateTime(b)).getMinutes();
    }

    public static final int getMinutesBetween(String a, String b) throws ParseException {
        return Minutes.minutesBetween(toJodaDateTime(a), toJodaDateTime(b)).getMinutes();
    }

    public static final int getSecondsBetween(Date a, Date b) {
        return Seconds.secondsBetween(toJodaDateTime(a), toJodaDateTime(b)).getSeconds();
    }

    public static final int getSecondsBetween(String a, String b) throws ParseException {
        return Seconds.secondsBetween(toJodaDateTime(a), toJodaDateTime(b)).getSeconds();
    }

    public static final DateTime toJodaDateTime(Date date) {
        return new DateTime(date);
    }

    public static final DateTime toJodaDateTime(String date) throws ParseException {
        return toJodaDateTime(parseDate(date));
    }

    public static final Date plusYears(Date date, int years) {
        return toJodaDateTime(date).plusYears(years).toDate();
    }

    public static final Date plusYears(String date, int years) throws ParseException {
        return toJodaDateTime(date).plusYears(years).toDate();
    }

    public static final Date plusMonths(Date date, int months) {
        return toJodaDateTime(date).plusMonths(months).toDate();
    }

    public static final Date plusMonths(String date, int months) throws ParseException {
        return toJodaDateTime(date).plusMonths(months).toDate();
    }

    public static final Date plusDays(Date date, int days) {
        return toJodaDateTime(date).plusDays(days).toDate();
    }

    public static final Date plusDays(String date, int days) throws ParseException {
        return toJodaDateTime(date).plusDays(days).toDate();
    }

    public static final Date plusHours(Date date, int hours) {
        return toJodaDateTime(date).plusHours(hours).toDate();
    }

    public static final Date plusHours(String date, int hours) throws ParseException {
        return toJodaDateTime(date).plusHours(hours).toDate();
    }

    public static final Date plusMinutes(Date date, int minutes) {
        return toJodaDateTime(date).plusMinutes(minutes).toDate();
    }

    public static final Date plusMinutes(String date, int minutes) throws ParseException {
        return toJodaDateTime(date).plusMinutes(minutes).toDate();
    }

    public static final Date plusSeconds(Date date, int seconds) {
        return toJodaDateTime(date).plusSeconds(seconds).toDate();
    }

    public static final Date plusSeconds(String date, int seconds) throws ParseException {
        return toJodaDateTime(date).plusSeconds(seconds).toDate();
    }

    public static final boolean isBetween(Date a, Date b, Date date) {
        if (a != null && b != null && date != null) {
            Interval interval = new Interval(toJodaDateTime(a), toJodaDateTime(b));
            return interval.contains(date.getTime()) || date.equals(b);
        } else {
            return false;
        }
    }

    public static final boolean isBetween(String a, String b, String date) throws ParseException {
        if (a != null && b != null && date != null) {
            Interval interval = new Interval(toJodaDateTime(a), toJodaDateTime(b));
            return interval.contains(toJodaDateTime(date)) || date.equals(b);
        } else {
            return false;
        }
    }

    public static final long getMillisBetween(Date a, Date b) {
        return (new Duration(toJodaDateTime(a), toJodaDateTime(b))).getMillis();
    }

    public static final long getMillisBetween(String a, String b) throws ParseException {
        return (new Duration(toJodaDateTime(a), toJodaDateTime(b))).getMillis();
    }

    public static final Date getLater(Date a, Date b) {
        if (a.equals(b)) {
            return null;
        } else {
            return a.after(b) ? a : b;
        }
    }

    public static final Date getEarlier(Date a, Date b) {
        if (a.equals(b)) {
            return null;
        } else {
            return a.before(b) ? a : b;
        }
    }

    public static final Date getFirstDayOfMonth(Date date) {
        return toJodaDateTime(date).dayOfMonth().withMinimumValue().toDate();
    }

    public static final Date getLastDayOfMonth(Date date) {
        return toJodaDateTime(date).dayOfMonth().withMaximumValue().toDate();
    }

    public static final int getYear(Date date) {
        return toJodaDateTime(date).getYear();
    }

    public static final int getYear(String date) throws ParseException {
        return toJodaDateTime(date).getYear();
    }

    public static final int getMonth(Date date) {
        return toJodaDateTime(date).getMonthOfYear();
    }

    public static final int getMonth(String date) throws ParseException {
        return toJodaDateTime(date).getMonthOfYear();
    }

    public static final int getDayOfWeek(Date date) {
        return toJodaDateTime(date).getDayOfWeek();
    }

    public static final int getDayOfWeek(String date) throws ParseException {
        return toJodaDateTime(date).getDayOfWeek();
    }

    public static final int getDay(Date date) {
        return toJodaDateTime(date).getDayOfMonth();
    }

    public static final int getDay(String date) throws ParseException {
        return toJodaDateTime(date).getDayOfMonth();
    }

    public static final int getHour(Date date) {
        return toJodaDateTime(date).getHourOfDay();
    }

    public static final int getHour(String date) throws ParseException {
        return toJodaDateTime(date).getHourOfDay();
    }

    public static final int getMinute(Date date) {
        return toJodaDateTime(date).getMinuteOfHour();
    }

    public static final int getMinute(String date) throws ParseException {
        return toJodaDateTime(date).getMinuteOfHour();
    }

    public static final int getSecond(Date date) {
        return toJodaDateTime(date).getSecondOfMinute();
    }

    public static final int getSecond(String date) throws ParseException {
        return toJodaDateTime(date).getSecondOfMinute();
    }

    public static int age(Date birthDay, Date dateToCompare) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateToCompare);
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("Birthday is after date {}!");
        } else {
            int year = cal.get(1);
            int month = cal.get(2);
            int dayOfMonth = cal.get(5);
            cal.setTime(birthDay);
            int age = year - cal.get(1);
            int monthBirth = cal.get(2);
            if (month == monthBirth) {
                int dayOfMonthBirth = cal.get(5);
                if (dayOfMonth < dayOfMonthBirth) {
                    --age;
                }
            } else if (month < monthBirth) {
                --age;
            }

            return age;
        }
    }

    public static List<String> getWeekly(int intervals) {
        List<String> pastDaysList = new ArrayList();

        for(int i = 1; i < intervals; ++i) {
            pastDaysList.add(getPastDate(i));
        }

        return pastDaysList;
    }

    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(6, calendar.get(6) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    public static int getWeeksfrom1970() {
        int days = getDaysBetween(parseDate("1970-01-01 00:00:00"), new Date());
        days += 3;
        return days / 7;
    }



    static {
        ((SimpleDateFormat)DEFAULT_DATE_FORMATTER.get()).setLenient(false);
        ((SimpleDateFormat)SLASH_DATE_FORMATTER.get()).setLenient(false);
        ((SimpleDateFormat)NOT_SLASH_DATE_FORMATTER.get()).setLenient(false);
        ((SimpleDateFormat)CN_DATE_FORMATTER.get()).setLenient(false);
        ((SimpleDateFormat)MONTH_DAY_YEAR_FORMATTER.get()).setLenient(false);
        ((SimpleDateFormat)DEFAULT_DATETIME_FORMATTER.get()).setLenient(false);
        ((SimpleDateFormat)SLASH_DATETIME_FORMATTER.get()).setLenient(false);
        ((SimpleDateFormat)NOT_SLASH_DATETIME_FORMATTER.get()).setLenient(false);
        ((SimpleDateFormat)DEFAULT_TIME_FORMATTER.get()).setLenient(false);
    }


    public static void main(String[] args) throws ParseException {
        Date lastOpenApp = com.dousnl.utils.date.DateUtil.parse("2021-03-26 11:30:43","yyyy-MM-dd hh:mm:ss");
        int daysBetween = DateUtil.getDaysBetween(lastOpenApp, new Date());
    System.out.println(daysBetween<=25);
    }
}
