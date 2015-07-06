package us.bibos.puzzleanddragonnotifier.DBContract.Model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class QueryDBModel extends DBModel<Cursor> {
    private String table;
    private String[] columns;
    private String selection;
    private String[] selectionArgs;
    private String groupBy;
    private String having;
    private String orderBy;
    private String limit;

    public QueryDBModel(String table, String[] columns, String selection, String[] selectionArgs,
                        String groupBy, String having, String orderBy, String limit) {
        this.table = table;
        this.columns = columns;
        this.selection = selection;
        this.selectionArgs = selectionArgs;
        this.groupBy = groupBy;
        this.having = having;
        this.orderBy = orderBy;
        this.limit = limit;
    }

    @Override
    public Cursor execute(SQLiteDatabase db) {
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }
}
