package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import us.bibos.puzzleanddragonnotifier.DBContract.Exceptions.DatabaseException;
import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;
import us.bibos.puzzleanddragonnotifier.Utils.Constants;

public class DBInsertAsyncTask extends DBAsyncTask {

    public DBInsertAsyncTask(DBModel model, SQLiteOpenHelper helper, Context context) {
        super(model, helper, context);
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(context, "Proceeding registration...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(DBModel... params) {
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            model.execute(db);
        }
        catch (DatabaseException e) {
            Log.i(Constants.APP_TAG, "Insert user info failed: " + e.getMessage());
            return "Failed";
        }
        return "Succeeded";
    }
}
