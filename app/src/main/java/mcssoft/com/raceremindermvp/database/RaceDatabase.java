package mcssoft.com.raceremindermvp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import mcssoft.com.raceremindermvp.interfaces.IRaceDAO;
import mcssoft.com.raceremindermvp.model.Race;

@Database(entities = {Race.class}, version = 1)
public abstract class RaceDatabase extends RoomDatabase {

    private static final String DB_NAME = "RaceDatabase.db";
    private static volatile RaceDatabase instance;

    static synchronized public RaceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RaceDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                RaceDatabase.class,
                DB_NAME).build();
    }

    public abstract IRaceDAO getRaceDAO();
}
