package mcssoft.com.raceremindermvp.interfaces.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;

import mcssoft.com.raceremindermvp.database.RaceDatabase;


public class RaceApp extends Application {

    @Inject
    RaceDatabase raceDatabase;

    public static RaceApp get(Context context) {
        return (RaceApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new RaceAppModule(this))
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getIAppComponent() {
        return applicationComponent;
    }

    protected ApplicationComponent applicationComponent;
}
