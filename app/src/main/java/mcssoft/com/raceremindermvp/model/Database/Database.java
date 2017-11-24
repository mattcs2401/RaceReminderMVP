package mcssoft.com.raceremindermvp.model.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

public class Database {

    public Database(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void destroy() {
        if(dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }
    }

    public Cursor getRecords(String tableName, @Nullable String[] projection, @Nullable String whereClause, @Nullable String[] selArgs) {

        if(dbHelper == null) {
            return null;
        }
        // sanity checks.
        if(projection == null) {
            projection = dbHelper.getProjection(tableName);
        }
        if(whereClause == null) {
            selArgs = null;
        } else if(selArgs == null) {
            whereClause = null;
        }

        Cursor cursor = null;
        SQLiteDatabase db = dbHelper.getDatabase();

        try {
            db.beginTransaction();
            cursor = db.query(tableName, projection, whereClause, selArgs, null, null, null);
        } catch(Exception ex) {
            Log.d("class:Database", ex.getMessage());
        } finally {
            if(db != null) {
                db.endTransaction();
            }
            return cursor;
        }
    }

    private static DatabaseHelper dbHelper;
    private static volatile Database instance = null;
}
