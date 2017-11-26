package mcssoft.com.raceremindermvp.model.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

public class Database extends SQLiteOpenHelper {

    public Database(Context context) {
        super(context, Schema.DATABASE_NAME, null, Schema.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.beginTransaction();
        try {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Schema.DATABASE_NAME + "." + Schema.RACE_DETAILS + ";");
            sqLiteDatabase.execSQL(Schema.DATABASE_CREATE);
            sqLiteDatabase.setTransactionSuccessful();
        } catch(SQLException sqle) {
            Log.d("class:Database","Exception thrown on database create: " + sqle.getMessage());
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Schema.DATABASE_NAME + "." + Schema.RACE_DETAILS + ";");
    }

    public void destroy() {
        if(sqLiteDatabase != null) {
            sqLiteDatabase.close();
            sqLiteDatabase = null;
        }
    }

    public Cursor getRecords(String tableName, @Nullable String[] projection, @Nullable String whereClause, @Nullable String[] selArgs) {

        // sanity checks.
        if(projection == null) {
            projection = getProjection(tableName);
        }
        if(whereClause == null) {
            selArgs = null;
        } else if(selArgs == null) {
            whereClause = null;
        }

        Cursor cursor = null;

        try {
            sqLiteDatabase.beginTransaction();
            cursor = sqLiteDatabase.query(tableName, projection, whereClause, selArgs, null, null, null);
        } catch(Exception ex) {
            Log.d("class:Database", ex.getMessage());
        } finally {
            if(sqLiteDatabase != null) {
                sqLiteDatabase.endTransaction();
            }
            return cursor;
        }
    }

    public static String [] getProjection(String tableName) {
        // TODO - custom projection, i.e. not all columns.
        switch (tableName) {
            case Schema.RACE_DETAILS:
                return getRaceDetailsProjection();
        }
        return  null;
    }

    private static final String[] getRaceDetailsProjection() {
        return new String[] {
                Schema.COLUMN_ROWID,
                Schema.COLUMN_CITY_CODE,
                Schema.COLUMN_RACE_CODE,
                Schema.COLUMN_RACE_NUM,
                Schema.COLUMN_RACE_SEL,
                Schema.COLUMN_DATE_TIME,
                Schema.COLUMN_D_CHG_REQ,
                Schema.COLUMN_NOTIFIED
        };
    }

    private SQLiteDatabase sqLiteDatabase;
}
