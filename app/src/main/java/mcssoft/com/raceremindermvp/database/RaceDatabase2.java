package mcssoft.com.raceremindermvp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class RaceDatabase2 extends SQLiteOpenHelper {
    private static RaceDatabase2 instance = null;

    static RaceDatabase2 getInstance(Context context) {
        if(instance ==  null) {
            instance = new RaceDatabase2(context);
        }
        return instance;
    }

    private RaceDatabase2(Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private static final String DATABASE_NAME = "database_name";
    private static final String DATABASE_TABLE = "table_name";
    private static final int DATABASE_VERSION = 1;
}
