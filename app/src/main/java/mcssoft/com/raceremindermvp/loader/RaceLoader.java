package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import mcssoft.com.raceremindermvp.database.RaceDatabase;

public class RaceLoader extends AsyncTaskLoader {

    public RaceLoader(Context context, RaceDatabase raceDatabase) {
        super(context);
    }

    @Override
    public Object loadInBackground() {
        return null;
    }
}
