package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;

public abstract class DBAsyncTask extends AsyncTask<DBModel, Void, String> {
    protected DBModel model;
    protected SQLiteOpenHelper helper;
    protected Context context;

    public DBAsyncTask(DBModel model, SQLiteOpenHelper helper, Context context) {
        this.model = model;
        this.helper = helper;
        this.context = context;
    }
}
