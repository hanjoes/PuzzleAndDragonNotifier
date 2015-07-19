package us.bibos.puzzleanddragonnotifier.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

import us.bibos.puzzleanddragonnotifier.Probe.PNDWikiProbe;
import us.bibos.puzzleanddragonnotifier.Probe.ProbeHandlers.NotificationHandler;

import static us.bibos.puzzleanddragonnotifier.Utils.Constants.APP_TAG;

public class PageProbingService extends IntentService {

    public static final String PND_CHINA_URL =
            "http://zh.pad.wikia.com/wiki/Homepage/Home?action=render";

    public PageProbingService() {
        super(PageProbingService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            Document doc = Jsoup.connect(PND_CHINA_URL).get();
            PNDWikiProbe probe = new PNDWikiProbe();
            probe.probAndExecute(doc, new NotificationHandler(intent, this));
        } catch (IOException e) {
            Log.e(APP_TAG, "Error occurred when trying to connect to website: " + e.getMessage());
        }
    }
}
