package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.Model.QueryDBModel;

public class DBQueryAsyncTask extends DBAsyncTask {

    public DBQueryAsyncTask(DBModel model, SQLiteOpenHelper helper, Context context) {
        super(model, helper, context);
    }

    @Override
    protected String doInBackground(DBModel... params) {
        return null;
    }
}
