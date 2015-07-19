package us.bibos.puzzleanddragonnotifier.Probe.ProbeHandlers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import us.bibos.puzzleanddragonnotifier.Notifier.SimpleTextNotifier;
import us.bibos.puzzleanddragonnotifier.Probe.PNDWikiProbe.NotificationProbeData;
import us.bibos.puzzleanddragonnotifier.Probe.ProbeData.ProbeData;
import us.bibos.puzzleanddragonnotifier.Utils.Constants;
import us.bibos.puzzleanddragonnotifier.Utils.DateTimeUtil;

import static us.bibos.puzzleanddragonnotifier.Utils.Constants.APP_TAG;
import static us.bibos.puzzleanddragonnotifier.Utils.Constants.ONE_HOUR;
import static us.bibos.puzzleanddragonnotifier.Utils.Constants.PND_ID_EXTRA;
import static us.bibos.puzzleanddragonnotifier.Utils.Constants.WIKI_TIMETABLE_TIMEZONE;
import static us.bibos.puzzleanddragonnotifier.Utils.DateTimeUtil.getDateFromChineseMonthDay;
import static us.bibos.puzzleanddragonnotifier.Utils.DateTimeUtil.getZonedYear;

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
                dateStr = getDateStringFromHeader(ths);
            }
            else {
                Elements tds = row.getElementsByTag("td");
                if (tds.size() == Constants.NUM_GROUPS) {
                    int columnNum = getColumnNumFromPndId();
                    for (int i = 0; i < tds.size(); ++i) {
                        if (i == columnNum) {
                            timeStr = tds.get(i).text();
                            processDateTimeInfo(dateStr + " " + timeStr + " " +
                                    getZonedYear(WIKI_TIMETABLE_TIMEZONE));
                        }
                    }
                }
            }
        }
    }

    @Nullable
    private String getDateStringFromHeader(Elements ths) {
        Element th = ths.get(0);
        String dateStr = th.text();
        boolean match = Pattern.matches("[0-9]+月[0-9]+日", dateStr);
        if (match) {
            return getDateFromChineseMonthDay(dateStr);
        }
        return null;
    }

    private void processDateTimeInfo(String dateStr) {
        try {
            Date remoteDate = DateTimeUtil.getRemoteZonedDateTime(dateStr,
                    WIKI_TIMETABLE_TIMEZONE);
            Date systemDate = new Date();
            Log.v(APP_TAG, "remote date: " + remoteDate + " time: " + remoteDate.getTime());
            Log.v(APP_TAG, "system date: " + systemDate + " time: " + systemDate.getTime());
            if (systemDate.getTime() - remoteDate.getTime() < ONE_HOUR) {
                SimpleTextNotifier notifier = new SimpleTextNotifier("Hunt time!!",
                        "Emergency Happening!", context);
                notifier.init_notification();
            }
        } catch (ParseException e) {
            Log.e(APP_TAG, "Parse date string error: " + dateStr);
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
