package mcssoft.com.raceremindermvp.model.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, Schema.DATABASE_NAME, null, Schema.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(LOG_TAG, "onCreate");

        db.beginTransaction();
        try {
            db.execSQL("DROP TABLE IF EXISTS " + Schema.DATABASE_NAME + "." + Schema.RACE_DETAILS + ";");
            db.execSQL(Schema.DATABASE_CREATE);
            db.setTransactionSuccessful();
        } catch(SQLException sqle) {
            Log.d(LOG_TAG, "Exception thrown on database create: " + sqle.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(this.getClass().getCanonicalName(), "onUpgrade");
        db.execSQL("DROP TABLE IF EXISTS " + Schema.DATABASE_NAME + "." + Schema.RACE_DETAILS + ";");
    }

    public SQLiteDatabase getDatabase() {
        return sqLiteDatabase;
    }

    public void close() {
        if((sqLiteDatabase != null) && (sqLiteDatabase.isOpen())) {
            sqLiteDatabase.close();
        }
        if(context != null) {
            context = null;
        }
    }

    public enum Projection {
        RaceDetailsSchema
    }

    public static String [] getProjection(Projection projection) {
        switch (projection) {
            case RaceDetailsSchema:
                return getRaceDetailsProjection();
        }
        return  null;
    }

    public static String [] getProjection(String tableName) {
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

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    private String LOG_TAG = this.getClass().getCanonicalName();
}
