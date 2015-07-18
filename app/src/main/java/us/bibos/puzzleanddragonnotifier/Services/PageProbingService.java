package us.bibos.puzzleanddragonnotifier.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import us.bibos.puzzleanddragonnotifier.Notifier.SimpleTextNotifier;
import us.bibos.puzzleanddragonnotifier.Utils.Constants;

import static us.bibos.puzzleanddragonnotifier.Utils.Constants.APP_TAG;
import static us.bibos.puzzleanddragonnotifier.Utils.Constants.PND_ID_EXTRA;

public class PageProbingService extends IntentService {

    public static final String PND_CHINA_URL =
            "http://zh.pad.wikia.com/wiki/Puzzle_%26_Dragons_" + "%E4%B8%AD%E6%96%87WIKI";

    public PageProbingService() {
        super(PageProbingService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(APP_TAG, "Handling Request...");

        try {
            Document doc = Jsoup.connect(PND_CHINA_URL).get();
            Log.i(APP_TAG, doc.text());
        } catch (IOException e) {
            Log.e(APP_TAG, "Error occurred when trying to connect to website: " + e.getMessage());
        }

        String pndId = (String) intent.getExtras().get(PND_ID_EXTRA);
        Date today = new Date();
        SimpleTextNotifier notifier = new SimpleTextNotifier("ID: " + pndId, today.toString(), this);
        notifier.init_notification();
    }
}
