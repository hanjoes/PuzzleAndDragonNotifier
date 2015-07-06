package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.Toast;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;

public abstract class DBAsyncTask<M, R> extends AsyncTask<DBModel<M>, Void, R> {
    protected DBModel<M> model;
    protected SQLiteOpenHelper helper;
    protected Context context;

    public DBAsyncTask(DBModel<M> model, SQLiteOpenHelper helper, Context context) {
        this.model = model;
        this.helper = helper;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Proceeding database operation...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(R result) {
        Toast.makeText(context, result.toString(), Toast.LENGTH_SHORT).show();
    }
}
