package us.bibos.puzzleanddragonnotifier.Services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

import us.bibos.puzzleanddragonnotifier.Notifier.SimpleTextNotifier;

import static us.bibos.puzzleanddragonnotifier.Utils.Constants.APP_TAG;

public class PageProbingService extends IntentService {

    public PageProbingService() {
        super(PageProbingService.class.getName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(APP_TAG, "Handling Request...");

        Date today = new Date();
        SimpleTextNotifier notifier = new SimpleTextNotifier("From background", today.toString(), this);
        notifier.init_notification();
    }
}
