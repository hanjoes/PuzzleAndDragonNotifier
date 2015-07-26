package us.bibos.notifier.Activities;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import us.bibos.notifier.DBContract.Model.InsertDBModel;
import us.bibos.notifier.DBContract.Model.QueryDBModel;
import us.bibos.notifier.DBContract.Model.UpdateDBModel;
import us.bibos.notifier.DBContract.UserInfoContract.UserInfo;
import us.bibos.notifier.DBHelper.UserInfoSQLiteHelper;
import us.bibos.notifier.R;
import us.bibos.notifier.Tasks.AlarmControlAsyncTask;
import us.bibos.notifier.Tasks.InitializationAsyncTask;
import us.bibos.notifier.Tasks.RegistrationAsyncTask;
import us.bibos.notifier.Tasks.UpdateUserAsyncTask;

public class HomeActivity extends Activity {

    private UserInfoSQLiteHelper helper;

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
        helper = new UserInfoSQLiteHelper(this);
        QueryDBModel model = new QueryDBModel(UserInfo.TABLE_NAME, null, null, null,
                null, null, null, null);
        AlarmControlAsyncTask alarmControlAsyncTask = new AlarmControlAsyncTask(
                model, helper.getReadableDatabase(), this);
        alarmControlAsyncTask.execute();
    }

    private void initialize() {
        helper = new UserInfoSQLiteHelper(this);
        QueryDBModel model = new QueryDBModel(UserInfo.TABLE_NAME, null, null, null,
                null, null, null, null);
        InitializationAsyncTask asyncTask = new InitializationAsyncTask(model,
                helper.getReadableDatabase(), this);
        asyncTask.execute();
    }

    public void huntButtonClicked(View view) {
        Intent huntIntent = new Intent(this, HuntingActivity.class);
        startActivity(huntIntent);
    }

}
