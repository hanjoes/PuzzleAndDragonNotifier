package us.bibos.notifier.Tasks;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.Toast;

import us.bibos.notifier.Activities.HomeActivity;
import us.bibos.notifier.DBContract.Model.DBModel;
import us.bibos.notifier.DBContract.UserInfoContract.UserInfo;
import us.bibos.notifier.R;
import us.bibos.notifier.Services.PageProbingService;

import static us.bibos.notifier.Utils.Constants.PND_ID_EXTRA;

public class AlarmControlAsyncTask extends DBAsyncTask<Cursor, Cursor> {
    private static final long INTERVAL_ONE_MINUTE = 10 * 1000;

    public AlarmControlAsyncTask(DBModel<Cursor> model, SQLiteDatabase db, Context context) {
        super(model, db, context);
    }

    @Override
    protected void onPostExecute(Cursor result) {
        boolean idExists = result.moveToFirst();
        if (idExists) {
            String pndId = result.getString(UserInfo.COLUMN_NAME_PND_ID_INDEX);
            String name = result.getString(UserInfo.COLUMN_NAME_NAME_INDEX);
            Intent intent = new Intent(context, PageProbingService.class);
            intent.putExtra(PND_ID_EXTRA, pndId);

            AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            PendingIntent alarmIntent = PendingIntent.getService(context, 0, intent,
                    PendingIntent.FLAG_NO_CREATE);
            boolean alarmStarted = (alarmIntent != null);

            // re-acquire the alarm
            alarmIntent = PendingIntent.getService(context, 0, intent, 0);

            HomeActivity activity = (HomeActivity) context;
            Button button = (Button) activity.findViewById(R.id.alarm_control_button);

            if (!alarmStarted) {
                alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                        INTERVAL_ONE_MINUTE, INTERVAL_ONE_MINUTE, alarmIntent);
                button.setText(R.string.alarm_control_button_stop);
                Toast.makeText(context, "Alarm has been set for you " + name,
                        Toast.LENGTH_SHORT).show();
            } else {
                alarmMgr.cancel(alarmIntent);
                alarmIntent.cancel();
                button.setText(R.string.alarm_control_button_hunt);
                Toast.makeText(context, "Alarm has been canceled for you " + name,
                        Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(context, "Sorry, please register first.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        return model.execute(db);
    }

}
