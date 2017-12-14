package mcssoft.com.raceremindermvp.interfaces.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class RaceAppModule {

    private final Application application;

    public RaceAppModule(Application app) {
        application = app;
    }

    @Provides
    @IAppContext
    Context provideContext() {
        return application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "RaceDatabase.db";
    }

//    @Provides
//    @DatabaseInfo
//    Integer provideDatabaseVersion() {
//        return 2;
//    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return application.getSharedPreferences("race_prefs", Context.MODE_PRIVATE);
    }
}
