package us.bibos.notifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Button;
import android.widget.Toast;

import us.bibos.notifier.DBContract.Model.InsertDBModel;
import us.bibos.notifier.Activities.HomeActivity;
import us.bibos.notifier.R;

public class RegistrationAsyncTask extends DBAsyncTask<Long, Boolean> {

    public RegistrationAsyncTask(InsertDBModel model, SQLiteDatabase db, Context context) {
        super(model, db, context);
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

        db.close();
    }

    @Override
    protected Boolean doInBackground(Void... ignored) {
        Long ret = model.execute(db);
        return ret != -1;
    }
}
