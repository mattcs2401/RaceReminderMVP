package mcssoft.com.raceremindermvp.utility;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class DbType {

    @StringDef({TName.MEETINGS, TName.RACES, TName.RUNNERS})

    @Retention(RetentionPolicy.SOURCE)

    public @interface TName {
        String MEETINGS = "MEETINGS";
        String RACES = "RACES";
        String RUNNERS = "RUNNERS";
    }
}
