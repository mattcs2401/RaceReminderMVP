package mcssoft.com.raceremindermvp.interfaces.di;

import dagger.Component;
import mcssoft.com.raceremindermvp.activity.MainActivity;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = RaceAppModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
