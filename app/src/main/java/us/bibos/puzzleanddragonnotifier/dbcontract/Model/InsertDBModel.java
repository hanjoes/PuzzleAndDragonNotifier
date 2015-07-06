package us.bibos.puzzleanddragonnotifier.DBContract.Model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class InsertDBModel extends DBModel<Long> {

    private ContentValues values;
    private String tableName;

    public InsertDBModel(ContentValues values, String tableName) {
        this.values = values;
        this.tableName = tableName;
    }

    @Override
    public Long execute(SQLiteDatabase db) {
        Long ret = db.insert(tableName, null, values);
        return ret;
    }
}
