package us.bibos.notifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import us.bibos.notifier.DBContract.Model.UpdateDBModel;

public class UpdateUserAsyncTask extends DBAsyncTask<Integer, String> {
    public UpdateUserAsyncTask(UpdateDBModel model, SQLiteDatabase db, Context context) {
        super(model, db, context);
    }

    @Override
    protected String doInBackground(Void... ignored) {
        Integer ret = model.execute(db);
        if (ret == 0) {
            return "Could not find user information";
        }
        db.close();
        return "Succeeded";
    }
}
