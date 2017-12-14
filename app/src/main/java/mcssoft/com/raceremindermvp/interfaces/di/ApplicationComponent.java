package mcssoft.com.raceremindermvp.interfaces.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.utility.SharedPrefsHelper;

@Singleton
@Component(modules = RaceAppModule.class)
public interface ApplicationComponent {

    void inject(RaceApp raceApp);

    @IAppContext
    Context getContext();

    Application getApplication();

    RaceDatabase getRaceDatabase();

//    DataManager getDataManager();

    SharedPrefsHelper getPreferenceHelper();

//    DbHelper getDbHelper();
}
