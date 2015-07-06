package us.bibos.puzzleanddragonnotifier;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.InsertDBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.UserInfoContract.UserInfo;
import us.bibos.puzzleanddragonnotifier.DBHelper.UserInfoSQLiteHelper;
import us.bibos.puzzleanddragonnotifier.Tasks.DBAsyncTask;
import us.bibos.puzzleanddragonnotifier.Tasks.RegistrationAsyncTask;

public class HomeActivity extends ActionBarActivity {

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
        Context context = getApplicationContext();
        EditText idEditor = (EditText) findViewById(R.id.id_editor);
        String id = idEditor.getText().toString();
        EditText nameEditor = (EditText) findViewById(R.id.name_editor);
        String name = nameEditor.getText().toString();

        ContentValues insertValues = new ContentValues();
        insertValues.put(UserInfo.COLUMN_NAME_PND_ID, id);
        insertValues.put(UserInfo.COLUMN_NAME_NAME, name);
        InsertDBModel model = new InsertDBModel(insertValues, UserInfo.TABLE_NAME);

        UserInfoSQLiteHelper helper = new UserInfoSQLiteHelper(context);
        RegistrationAsyncTask task = new RegistrationAsyncTask(model, helper, context);
        task.execute(model);
    }

    public void idUpdateButtonClicked(View view) {
    }


    private void initialize() {
        // check whether user has registered before.
        boolean registered = checkUserRegistration();
        // enable/disable buttons accordingly.
        findViewById(R.id.info_update_button).setEnabled(registered);
        findViewById(R.id.info_save_button).setEnabled(!registered);
    }

    private boolean checkUserRegistration() {

        return false;
    }
}
