package us.bibos.puzzleanddragonnotifier.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import us.bibos.puzzleanddragonnotifier.DBContract.UserInfoContract.UserInfo;

public class UserInfoSQLiteHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserInfo.TABLE_NAME + " (" +
                    UserInfo._ID + " INTEGER PRIMARY KEY," +
                    UserInfo.COLUMN_NAME_PND_ID + TEXT_TYPE + COMMA_SEP +
                    UserInfo.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    UserInfo.COLUMN_NAME_DATE + TEXT_TYPE + ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserInfo.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserInfo.db";

    public UserInfoSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: don't lose data when upgraded
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
