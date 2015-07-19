package us.bibos.puzzleanddragonnotifier.Notifier;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import us.bibos.puzzleanddragonnotifier.R;
import us.bibos.puzzleanddragonnotifier.Utils.Constants;

public class SimpleTextNotifier extends Notifier implements doNotify {
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public SimpleTextNotifier(String title, String text, Context context) {
        builder = new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(title)
                        .setContentText(text);
        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void init_notification() {
        notificationManager.notify(SIMPLE_NOTIFIER, builder.build());
    }
}
