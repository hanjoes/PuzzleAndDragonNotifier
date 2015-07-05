package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.Model.UpdateDBModel;

public class DBUpdateAsyncTask extends DBAsyncTask {

    public DBUpdateAsyncTask(DBModel model, SQLiteOpenHelper helper, Context context) {
        super(model, helper, context);
    }

    @Override
    protected String doInBackground(DBModel... params) {
        return null;
    }
}
