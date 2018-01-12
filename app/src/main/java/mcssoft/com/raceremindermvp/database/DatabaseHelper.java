package mcssoft.com.raceremindermvp.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static DatabaseHelper getInstance(Context context) {
        /* Note: According to the doco this method is expensive, but if we try something like
           'private static DatabaseHelper instance = new DatabaseHelper(Context context)', we get
           "illegal forward reference".
        */
        if(instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        sqLiteDb.beginTransaction();
        try {
            sqLiteDb.execSQL(DatabaseConstants.DROP_MEETINGS_TABLE);
            sqLiteDb.execSQL(DatabaseConstants.DROP_RACES_TABLE);
            sqLiteDb.execSQL(DatabaseConstants.DROP_RUNNERS_TABLE);
            sqLiteDb.execSQL(DatabaseConstants.CREATE_MEETINGS_TABLE);
            sqLiteDb.execSQL(DatabaseConstants.CREATE_RACES_TABLE);
            sqLiteDb.execSQL(DatabaseConstants.CREATE_RUNNERS_TABLE);
            sqLiteDb.setTransactionSuccessful();
        } catch(SQLException ex) {
            Log.d(context.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            sqLiteDb.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.DATABASE_NAME + "." + DatabaseConstants.MEETINGS_TABLE + ";");
        this.sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.DATABASE_NAME + "." + DatabaseConstants.RACES_TABLE + ";");
        this.sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.DATABASE_NAME + "." + DatabaseConstants.RUNNERS_TABLE + ";");
    }

    public SQLiteDatabase getDatabase() {
        return sqLiteDatabase;
    }

    public enum Projection {
        MeetingSchema, RaceSchema, RunnerSchema
    }

    /**
     * Housekeeping activities.
     */
    public void onDestroy() {
        if(sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
        if(context != null) {
            context = null;
        }
    }

    private DatabaseHelper(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
        sqLiteDatabase = this.getWritableDatabase();
        this.context = context;
    }

    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private static DatabaseHelper instance = null;
}
