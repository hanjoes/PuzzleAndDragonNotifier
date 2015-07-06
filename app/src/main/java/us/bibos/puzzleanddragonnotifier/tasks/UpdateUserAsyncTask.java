package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.Model.UpdateDBModel;

public class UpdateUserAsyncTask extends DBAsyncTask<Integer, String> {
    public UpdateUserAsyncTask(UpdateDBModel model, SQLiteOpenHelper helper, Context context) {
        super(model, helper, context);
    }

    @Override
    protected String doInBackground(DBModel<Integer>... params) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Integer ret = model.execute(db);
        if (ret == 0) {
            return "Could not find user information";
        }
        return "Succeeded";
    }
}
