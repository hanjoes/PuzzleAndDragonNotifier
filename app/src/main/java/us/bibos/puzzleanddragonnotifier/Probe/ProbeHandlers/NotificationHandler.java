package us.bibos.puzzleanddragonnotifier.Probe.ProbeHandlers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

import us.bibos.puzzleanddragonnotifier.Notifier.SimpleTextNotifier;
import us.bibos.puzzleanddragonnotifier.Probe.PNDWikiProbe.NotificationProbeData;
import us.bibos.puzzleanddragonnotifier.Probe.ProbeData.ProbeData;
import us.bibos.puzzleanddragonnotifier.Utils.Constants;

import static us.bibos.puzzleanddragonnotifier.Utils.Constants.APP_TAG;
import static us.bibos.puzzleanddragonnotifier.Utils.Constants.ONE_HOUR;
import static us.bibos.puzzleanddragonnotifier.Utils.Constants.PND_ID_EXTRA;
import static us.bibos.puzzleanddragonnotifier.Utils.Constants.WIKI_TIMETABLE_TIMEZONE;

public class NotificationHandler implements ProbeHandler {

    private Context context;
    private Intent intent;

    public NotificationHandler(Intent intent, Context context) {
        this.intent = intent;
        this.context = context;
    }

    @Override
    public void handle(ProbeData data) {
        NotificationProbeData notificationProbeData = (NotificationProbeData) data;
        Element timetable = notificationProbeData.timeTable;
        Elements tables = timetable.getElementsByTag("table");
        // There should be only one table, currently.
        Element table = tables.first();

        Elements rows = table.getElementsByTag("tr");
        String timeStr;
        String dateStr = null;
        for (Element row : rows) {
            Elements ths = row.getElementsByTag("th");
            if (ths.size() == 1) {//potentially a date header
                dateStr = getDateFromHeader(ths);
            }
            else {
                Elements tds = row.getElementsByTag("td");
                if (tds.size() == Constants.NUM_GROUPS) {
                    int columnNum = getColumnNumFromPndId();
                    for (int i = 0; i < tds.size(); ++i) {
                        if (i == columnNum) {
                            timeStr = tds.get(i).text();
                            dealWithTimeInfo(dateStr + " " + timeStr + " " + getYear());
                        }
                    }
                }
            }
        }
    }

    private String getYear() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone(WIKI_TIMETABLE_TIMEZONE));
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    @Nullable
    private String getDateFromHeader(Elements ths) {
        Element th = ths.get(0);
        String dateStr = th.text();
        boolean match = Pattern.matches("[0-9]+月[0-9]+日", dateStr);
        if (match) {
            int monthEndIndex = dateStr.indexOf("月");
            String month = dateStr.substring(0, monthEndIndex);
            int dayEndIndex = dateStr.indexOf("日");
            String day = dateStr.substring(monthEndIndex + 1, dayEndIndex);
            return month + day;
        }
        return null;
    }

    private void dealWithTimeInfo(String time) {
        DateFormat format = new SimpleDateFormat("MMdd HH:mm yyyy", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone(WIKI_TIMETABLE_TIMEZONE));
        try {
            Date remoteDate = format.parse(time);
            Date localDate = new Date();
            if (remoteDate.getTime() - localDate.getTime() < ONE_HOUR) {
                SimpleTextNotifier notifier = new SimpleTextNotifier("Hunt time!!",
                        "Emergency Happening!", context);
                notifier.init_notification();
            }
            Log.i(APP_TAG, "Got date: " + remoteDate);
        } catch (ParseException e) {
            Log.e(APP_TAG, "Parse error: " + time);
        }
    }

    private int getColumnNumFromPndId() {
        String pndId = (String) intent.getExtras().get(PND_ID_EXTRA);
        if (pndId == null) return 0;
        String digit = pndId.substring(2, 3);
        switch (digit) {
            case "0":case "5":
                return 0;
            case "1":case "6":
                return 1;
            case "2":case "7":
                return 2;
            case "3":case "8":
                return 3;
            case "4":case "9":
                return 4;
            default:
                Log.e(APP_TAG, "PND ID " + pndId + " not legal.");
                return 0;
        }
    }
}
