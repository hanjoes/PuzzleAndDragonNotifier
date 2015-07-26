package us.bibos.notifier.DBContract.Model;

import android.database.sqlite.SQLiteDatabase;

public abstract class DBModel<T> {
    public abstract T execute(SQLiteDatabase db);
}
