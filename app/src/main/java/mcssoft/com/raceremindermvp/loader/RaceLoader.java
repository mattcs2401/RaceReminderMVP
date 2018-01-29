package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.database.RaceDatabase;

public class RaceLoader extends AsyncTaskLoader<Object> {

    public RaceLoader(Context context, RaceDatabase raceDatabase, Bundle bundle) {
        super(context);
        this.bundle = bundle;
        this.raceDatabase = raceDatabase;
    }

    @Override
    public Object loadInBackground() {
        return null;
    }

    @Override
    public void deliverResult(Object newResult) {
        theResult = newResult;
        if (isStarted()) {
            // If the Loader is currently started, we can immediately deliver its results.
            super.deliverResult(newResult);
        }
    }

    @Override
    protected void onStartLoading() {
        if(theResult != null) {
            // If we currently have a result available, deliver it now.
            deliverResult(theResult);
        }

        if(takeContentChanged() || theResult == null) {
            // If the data has changed since the last time it was loaded or is not currently
            // available, start a load.
            forceLoad();
        }
    }

    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        // Ensure the loader is stopped.
        onStopLoading();

        // At this point we can release the resources associated with 'theResult' if needed.
        if (theResult != null) {
            theResult = null;
        }
    }

    private Bundle bundle;
    private Object theResult;
    private RaceDatabase raceDatabase;
}
