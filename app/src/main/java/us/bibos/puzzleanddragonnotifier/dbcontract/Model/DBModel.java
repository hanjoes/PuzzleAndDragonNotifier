package us.bibos.puzzleanddragonnotifier.DBContract.Model;

import android.database.sqlite.SQLiteDatabase;

import us.bibos.puzzleanddragonnotifier.DBContract.Exceptions.DatabaseException;

public abstract class DBModel {
    public void execute(SQLiteDatabase db) {}
}
