package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import java.util.List;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.model.impl.MainModelImpl;

/**
 * Utility class that provides the background thread for the Room database object.
 */
public class RaceLoader extends AsyncTaskLoader<Object> {

    public RaceLoader(Context context, RaceDatabase raceDatabase, Bundle bundle) {
        super(context);
        this.bundle = bundle;
        this.raceDatabase = raceDatabase;
    }

    @Override
    public Object loadInBackground() {
        //String opType = bundle.getString("key");
        Object object = null;
        MainModelImpl.OpType opType = MainModelImpl.OpType.valueOf(bundle.getString("key"));
        switch(opType) {
            case MEETINGS_COUNT:
                int count = raceDatabase.getMeetingDAO().getMeetingsCount("N");
                object = count;
                break;
        }
        // TBA - Room database stuff.
        return object;
    }


    @Override
    public void deliverResult(Object newResult) {
//        List oldList = theResult;
        theResult = newResult;
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
/*
Example:
https://developer.android.com/reference/android/content/AsyncTaskLoader.html
 */