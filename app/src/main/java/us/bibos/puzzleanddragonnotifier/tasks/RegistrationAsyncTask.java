package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;

public class RegistrationAsyncTask extends DBAsyncTask<Long> {

    public RegistrationAsyncTask(DBModel<Long> model, SQLiteOpenHelper helper, Context context) {
        super(model, helper, context);
    }

    @Override
    protected String doInBackground(DBModel<Long>... params) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Long ret = model.execute(db);

        if (ret == -1) return "Registration failed";
        else return "Succeeded";
    }
}
