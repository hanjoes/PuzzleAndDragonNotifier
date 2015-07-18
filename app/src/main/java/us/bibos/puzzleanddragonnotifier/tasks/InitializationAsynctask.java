package us.bibos.puzzleanddragonnotifier.Tasks;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.EditText;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.QueryDBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.UserInfoContract.UserInfo;
import us.bibos.puzzleanddragonnotifier.Activities.HomeActivity;
import us.bibos.puzzleanddragonnotifier.R;
import us.bibos.puzzleanddragonnotifier.Services.PageProbingService;

import static us.bibos.puzzleanddragonnotifier.Utils.Constants.PND_ID_EXTRA;

public class InitializationAsyncTask extends DBAsyncTask<Cursor, Cursor> {
    public InitializationAsyncTask(QueryDBModel model, SQLiteDatabase db, Context context) {
        super(model, db, context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Cursor result) {
        HomeActivity activity = (HomeActivity) context;
        boolean registered = result.moveToFirst();
        if (registered) {
            String pndId = result.getString(UserInfo.COLUMN_NAME_PND_ID_INDEX);
            String name = result.getString(UserInfo.COLUMN_NAME_NAME_INDEX);
            EditText idEditor = (EditText) activity.findViewById(R.id.id_editor);
            idEditor.setText(pndId);
            EditText nameEditor = (EditText) activity.findViewById(R.id.name_editor);
            nameEditor.setText(name);

            initializeHuntingButton(activity, pndId);
        }
        Button updateButton = (Button) activity.findViewById(R.id.info_update_button);
        updateButton.setEnabled(registered);
        Button saveButton = (Button) activity.findViewById(R.id.info_save_button);
        saveButton.setEnabled(!registered);

        db.close();
    }

    private void initializeHuntingButton(HomeActivity activity, String pndId) {
        Intent intent = new Intent(context, PageProbingService.class);
        intent.putExtra(PND_ID_EXTRA, pndId);
        PendingIntent alarmIntent = PendingIntent.getService(context, 0, intent,
                PendingIntent.FLAG_NO_CREATE);
        if (alarmIntent != null) {
            Button alarmButton = (Button) activity.findViewById(R.id.alarm_control_button);
            alarmButton.setText(R.string.alarm_control_button_stop);
        }
    }

    @Override
    protected Cursor doInBackground(Void... ignored) {
        Cursor cursor = model.execute(this.db);
        return cursor;
    }
}
