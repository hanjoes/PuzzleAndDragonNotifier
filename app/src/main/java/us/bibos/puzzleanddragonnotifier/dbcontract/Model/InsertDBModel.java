package us.bibos.puzzleanddragonnotifier.DBContract.Model;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import us.bibos.puzzleanddragonnotifier.DBContract.Exceptions.DatabaseException;

public class InsertDBModel extends DBModel {

    private ContentValues values;
    private String tableName;

    public InsertDBModel(ContentValues values, String tableName) {
        this.values = values;
        this.tableName = tableName;
    }

    @Override
    public void execute(SQLiteDatabase db) {
        if (db.insert(tableName, null, values) == -1) {
            throw new DatabaseException("Error occurred when inserting in to table " + tableName);
        }
    }
}
