package us.bibos.notifier.Notifier;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import us.bibos.notifier.R;

public class SimpleTextNotifier extends Notifier {
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public SimpleTextNotifier(String title, String text, Context context) {
        builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(text)
                .setVibrate(new long[]{500, 100, 100, 1000, 1000, 1000, 1000, 1000})
                .setLights(Color.YELLOW, ONE_SEC, ONE_THIRD_SEC);

        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.golden);
        builder.setSound(uri);
        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void init_notification() {
        notificationManager.notify(SIMPLE_NOTIFIER, builder.build());
    }
}
