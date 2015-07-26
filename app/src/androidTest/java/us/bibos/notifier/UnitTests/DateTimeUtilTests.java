package us.bibos.notifier.UnitTests;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import us.bibos.notifier.Utils.Constants;
import us.bibos.notifier.Utils.DateTimeUtil;


public class DateTimeUtilTests extends TestCase {

    public static final String TEST_REMOTE_DATE_STR = "0719 10:00 2015";

    public void testGetRemoteZonedDate() throws ParseException {
        Date remoteDate = DateTimeUtil.getRemoteZonedDateTime(TEST_REMOTE_DATE_STR,
                Constants.WIKI_TIMETABLE_TIMEZONE);
        assertEquals("Sat Jul 18 19:00:00 PDT 2015", remoteDate.toString());
    }

    public void testGetDefaultDateStr() {
        String dateStr = DateTimeUtil.getDefaultDateStr();
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone(Constants.WIKI_TIMETABLE_TIMEZONE));
        String month = String.valueOf((calendar.get(Calendar.MONTH) + 1));
        if (month.length() == 1) month = "0" + month;
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        if (day.length() == 1) day = "0" + day;
        assertEquals(month + day, dateStr);
    }
}
