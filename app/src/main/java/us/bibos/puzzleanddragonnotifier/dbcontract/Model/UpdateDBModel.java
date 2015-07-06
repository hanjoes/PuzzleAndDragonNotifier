package us.bibos.puzzleanddragonnotifier.DBContract.Model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class UpdateDBModel extends DBModel<Integer> {

    private String tableName;
    private ContentValues values;
    private String where;
    private String[] whereArgs;

    public UpdateDBModel(String tableName, ContentValues values, String where, String[] whereArgs) {
        this.tableName = tableName;
        this.values = values;
        this.where = where;
        this.whereArgs = whereArgs;
    }

    @Override
    public Integer execute(SQLiteDatabase db) {
        return db.update(tableName, values, where, whereArgs);
    }
}
