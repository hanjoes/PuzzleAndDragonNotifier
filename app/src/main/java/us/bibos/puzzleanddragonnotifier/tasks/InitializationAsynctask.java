package us.bibos.puzzleanddragonnotifier.Tasks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.EditText;

import us.bibos.puzzleanddragonnotifier.DBContract.Model.DBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.Model.QueryDBModel;
import us.bibos.puzzleanddragonnotifier.DBContract.UserInfoContract.UserInfo;
import us.bibos.puzzleanddragonnotifier.HomeActivity;
import us.bibos.puzzleanddragonnotifier.R;

public class InitializationAsyncTask extends DBAsyncTask<Cursor, Cursor> {
    public InitializationAsyncTask(QueryDBModel model, SQLiteOpenHelper helper, Context context) {
        super(model, helper, context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Cursor result) {
        HomeActivity activity = (HomeActivity) context;
        boolean needsUpdate = result.moveToFirst();
        if (needsUpdate) {
            String pndId = result.getString(UserInfo.COLUMN_NAME_PND_ID_INDEX);
            String name = result.getString(UserInfo.COLUMN_NAME_NAME_INDEX);
            EditText idEditor = (EditText) activity.findViewById(R.id.id_editor);
            idEditor.setText(pndId);
            EditText nameEditor = (EditText) activity.findViewById(R.id.name_editor);
            nameEditor.setText(name);
        }
        Button updateButton = (Button) activity.findViewById(R.id.info_update_button);
        updateButton.setEnabled(needsUpdate);
        Button saveButton = (Button) activity.findViewById(R.id.info_save_button);
        saveButton.setEnabled(!needsUpdate);
    }

    @Override
    protected Cursor doInBackground(DBModel<Cursor>... params) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = model.execute(db);
        return cursor;
    }
}
