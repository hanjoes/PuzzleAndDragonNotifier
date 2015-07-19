package us.bibos.puzzleanddragonnotifier.UnitTests;

import junit.framework.TestCase;

import java.text.ParseException;
import java.util.Date;

import us.bibos.puzzleanddragonnotifier.Utils.Constants;
import us.bibos.puzzleanddragonnotifier.Utils.DateTimeUtil;


public class DateTimeUtilTests extends TestCase {

    public static final String TEST_REMOTE_DATE_STR = "0719 10:00 2015";

    public void testGetRemoteZonedDate() throws ParseException {
        Date remoteDate = DateTimeUtil.getRemoteZonedDateTime(TEST_REMOTE_DATE_STR,
                Constants.WIKI_TIMETABLE_TIMEZONE);
        assertEquals("Sat Jul 18 19:00:00 PDT 2015", remoteDate.toString());
    }
}
