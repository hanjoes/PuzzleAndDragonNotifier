package us.bibos.puzzleanddragonnotifier.Activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.InsertDBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.Model.QueryDBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.Model.UpdateDBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.UserInfoContract.UserInfo;
import us.bibos.puzzleanddragonnotifier.DBHelper.UserInfoSQLiteHelper;
import us.bibos.puzzleanddragonnotifier.R;
import us.bibos.puzzleanddragonnotifier.Services.PageProbingService;
import us.bibos.puzzleanddragonnotifier.Tasks.InitializationAsyncTask;
import us.bibos.puzzleanddragonnotifier.Tasks.RegistrationAsyncTask;
import us.bibos.puzzleanddragonnotifier.Tasks.UpdateUserAsyncTask;

public class HomeActivity extends Activity {

    private static final long INTERVAL_ONE_MINUTE = 10 * 1000;
    private UserInfoSQLiteHelper helper;
    private boolean alarmSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initialize();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void idSaveButtonClicked(View view) {
        EditText idEditor = (EditText) findViewById(R.id.id_editor);
        String id = idEditor.getText().toString();
        EditText nameEditor = (EditText) findViewById(R.id.name_editor);
        String name = nameEditor.getText().toString();

        ContentValues insertValues = new ContentValues();
        insertValues.put(UserInfo.COLUMN_NAME_PND_ID, id);
        insertValues.put(UserInfo.COLUMN_NAME_NAME, name);
        InsertDBModel model = new InsertDBModel(insertValues, UserInfo.TABLE_NAME);

        RegistrationAsyncTask task = new RegistrationAsyncTask(model,
                helper.getWritableDatabase(), this);
        task.execute();
    }

    public void idUpdateButtonClicked(View view) {
        EditText idEditor = (EditText) findViewById(R.id.id_editor);
        String id = idEditor.getText().toString();
        EditText nameEditor = (EditText) findViewById(R.id.name_editor);
        String name = nameEditor.getText().toString();

        ContentValues updateValue = new ContentValues();
        updateValue.put(UserInfo.COLUMN_NAME_PND_ID, id);
        updateValue.put(UserInfo.COLUMN_NAME_NAME, name);
        UpdateDBModel model = new UpdateDBModel(UserInfo.TABLE_NAME, updateValue, null, null);

        UpdateUserAsyncTask task = new UpdateUserAsyncTask(model,
                helper.getWritableDatabase(), this);
        task.execute();
    }

    public void alarmControlButtonClicked(View view) {
        AlarmManager alarmMgr = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, PageProbingService.class);
        PendingIntent alarmIntent = PendingIntent.getService(this, 0, intent, 0);
        if (!alarmSet) {
            alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    INTERVAL_ONE_MINUTE, INTERVAL_ONE_MINUTE, alarmIntent);
            startHunting();
        }
        else {
            alarmMgr.cancel(alarmIntent);
            stopHunting();
        }
    }

    private void startHunting() {
        alarmSet = true;
        Button button = (Button) findViewById(R.id.alarm_control_button);
        button.setText(R.string.alarm_control_button_stop);
    }

    private void stopHunting() {
        alarmSet = false;
        Button button = (Button) findViewById(R.id.alarm_control_button);
        button.setText(R.string.alarm_control_button_hunt);
    }

    private void initialize() {
        helper = new UserInfoSQLiteHelper(this);
        QueryDBModel model = new QueryDBModel(UserInfo.TABLE_NAME, null, null, null,
                null, null, null, null);
        InitializationAsyncTask asyncTask = new InitializationAsyncTask(model,
                helper.getReadableDatabase(), this);
        asyncTask.execute();

        boolean alarmSet = isAlarmSet();
        if (alarmSet) {
            Button alarmControlButton = (Button) findViewById(R.id.alarm_control_button);
            alarmControlButton.setText(R.string.alarm_control_button_stop);
        }
    }

    private boolean isAlarmSet() {
        Intent probingServiceIntent = new Intent(this, PageProbingService.class);
        PendingIntent alarmIntent = PendingIntent.getService(this, 0,
                probingServiceIntent, PendingIntent.FLAG_NO_CREATE);
        alarmSet = (alarmIntent != null);
        return alarmSet;
    }

}
