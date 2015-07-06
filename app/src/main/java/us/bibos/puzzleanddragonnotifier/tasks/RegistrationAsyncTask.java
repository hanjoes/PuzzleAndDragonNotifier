package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.Toast;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.Model.InsertDBModel;
import us.bibos.puzzleanddragonnotifier.HomeActivity;
import us.bibos.puzzleanddragonnotifier.R;

public class RegistrationAsyncTask extends DBAsyncTask<Long, Boolean> {

    public RegistrationAsyncTask(InsertDBModel model, SQLiteOpenHelper helper, Context context) {
        super(model, helper, context);
    }

    @Override
    protected void onPostExecute(Boolean succeeded) {
        HomeActivity activity = (HomeActivity) context;
        Button updateButton = (Button) activity.findViewById(R.id.info_update_button);
        updateButton.setEnabled(succeeded);
        Button saveButton = (Button) activity.findViewById(R.id.info_save_button);
        saveButton.setEnabled(!succeeded);

        if (succeeded) {
            Toast.makeText(context, "Succeeded", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected Boolean doInBackground(DBModel<Long>... params) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Long ret = model.execute(db);
        return ret != -1;
    }
}
