package us.bibos.notifier.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import static us.bibos.notifier.Utils.Constants.DEFAULT_DATE_FORMAT;
import static us.bibos.notifier.Utils.Constants.DEFAULT_PROBING_FORMAT;
import static us.bibos.notifier.Utils.Constants.WIKI_TIMETABLE_TIMEZONE;

public class DateTimeUtil {



    public static String getDateFromChineseMonthDay(String dateStr) {
        int monthEndIndex = dateStr.indexOf("月");
        String month = dateStr.substring(0, monthEndIndex);
        int dayEndIndex = dateStr.indexOf("日");
        String day = dateStr.substring(monthEndIndex + 1, dayEndIndex);
        return month + day;
    }

    public static String getZonedYear(String zoneId) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone(zoneId));
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static Date getRemoteZonedDateTime(String dateStr, String zoneId)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_PROBING_FORMAT, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone(zoneId));
        return formatter.parse(dateStr);
    }

    public static String getDefaultDateStr() {
        SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.US);
        formatter.setTimeZone(TimeZone.getTimeZone(WIKI_TIMETABLE_TIMEZONE));
        return formatter.format(new Date());
    }
}
