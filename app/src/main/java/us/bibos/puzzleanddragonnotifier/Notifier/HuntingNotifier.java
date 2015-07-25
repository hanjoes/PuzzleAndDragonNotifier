package us.bibos.puzzleanddragonnotifier.Notifier;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import us.bibos.puzzleanddragonnotifier.Activities.HomeActivity;
import us.bibos.puzzleanddragonnotifier.Activities.HuntingActivity;
import us.bibos.puzzleanddragonnotifier.R;

public class HuntingNotifier extends Notifier {
    private NotificationCompat.Builder builder;
    private NotificationManager notificationManager;

    public HuntingNotifier(String title, String content, Context context) {
        builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .setVibrate(new long[]{500, 100, 100, 1000, 1000, 1000, 1000, 1000})
                .setLights(Color.YELLOW, ONE_SEC, ONE_THIRD_SEC);

        Uri uri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.golden);
        builder.setSound(uri);

        Intent huntingIntent = new Intent(context, HuntingActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(HuntingActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(huntingIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);

        notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public void init_notification() {
        notificationManager.notify(REGULAR_ACTIVITY_NOTIFIER, builder.build());
    }
}
