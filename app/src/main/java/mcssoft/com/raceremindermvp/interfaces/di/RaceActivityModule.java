package mcssoft.com.raceremindermvp.interfaces.di;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;

@Module
public class RaceActivityModule {

    private Activity activity;

    public RaceActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @IActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    Activity provideActivity() {
        return activity;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return "RaceDatabase.db";
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return activity.getSharedPreferences("race_prefs", Context.MODE_PRIVATE);
    }
}
