package mcssoft.com.raceremindermvp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.interfaces.room.IRaceDAO;
import mcssoft.com.raceremindermvp.model.Race;
import mcssoft.com.raceremindermvp.utility.Resources;

@Database(entities = {Race.class}, version=1, exportSchema = false)
public abstract class RaceDatabase extends RoomDatabase {

    static synchronized public RaceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    public abstract IRaceDAO getRaceDAO();  // for Room.

    private static RaceDatabase create(final Context context) {
        return Room.databaseBuilder(context, RaceDatabase.class, Resources.getInstance(context).getString(R.string.database_name)).build();
    }

//    private static final String DB_NAME = "RaceDatabase.db";
    private static volatile RaceDatabase instance;

}
