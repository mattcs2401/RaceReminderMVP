package mcssoft.com.raceremindermvp.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import mcssoft.com.raceremindermvp.interfaces.di.ApplicationContext;
import mcssoft.com.raceremindermvp.interfaces.room.IRaceDAO;

@Singleton
public abstract class RaceDatabase extends RoomDatabase {

    @Inject
    static synchronized public RaceDatabase getInstance(@ApplicationContext Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static RaceDatabase create(final Context context) {
        return Room.databaseBuilder(context, RaceDatabase.class, DB_NAME).build();
    }

    public abstract IRaceDAO getRaceDAO();

    private static final String DB_NAME = "RaceDatabase.db";
    private static volatile RaceDatabase instance;

}
