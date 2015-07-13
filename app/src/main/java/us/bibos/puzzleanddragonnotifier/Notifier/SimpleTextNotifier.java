package us.bibos.puzzleanddragonnotifier.Notifier;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import us.bibos.puzzleanddragonnotifier.R;
import us.bibos.puzzleanddragonnotifier.Utils.Constants;

public class SimpleTextNotifier extends Notifier implements doNotify {
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public SimpleTextNotifier(String title, String text, Activity activity) {
        builder = new NotificationCompat.Builder(activity)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text);
        notificationManager =
                (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void init_notification() {
        Log.i(Constants.APP_TAG, "Sending Notification....");
        notificationManager.notify(SIMPLE_NOTIFIER, builder.build());
    }
}
