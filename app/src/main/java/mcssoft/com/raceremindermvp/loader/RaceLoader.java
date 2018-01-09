package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.CursorLoader;
import android.os.Bundle;
import android.util.Log;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.ILoaderRace;

public class RaceLoader extends AsyncTaskLoader<Object> {

    public RaceLoader(Context context, RaceDatabase raceDatabase, Bundle args, ILoaderRace iLoaderRace) {
        super(context);
        this.args = args;
        this.iLoaderRace = iLoaderRace;
        this.raceDatabase = raceDatabase;
    }

    @Override
    protected void onStartLoading() {
//        super.onStartLoading();
        if(data != null) {
            deliverResult(data);
        }
        if (takeContentChanged() || data == null) {// || configChange) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    @Override
    public Object loadInBackground() {
        Log.d("loadInBackGround", "in loadinInBackground");
        // testing only.
        Object object = new Object();
        try {
            object = raceDatabase.getMeetingDAO().selectAll("N");
//        return null;
        } catch (Exception ex) {
            Log.d("loadInBackground: ", ex.getMessage());
        }
        finally {
            return object;
        }
    }

    @Override
    public void deliverResult(Object data) {
        if(isReset()) {
            if(data != null) {
                onReleaseResources(data);
            }
        }

        Object oldData = data;
        this.data = data;

        if(isStarted()) {
            super.deliverResult(data);
        }
        if(oldData != null) {
            onReleaseResources(oldData);
        }
        iLoaderRace.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
//        super.onStopLoading();
        cancelLoad();
    }

    @Override
    public void onCanceled(Object data) {
        super.onCanceled(data);
        onReleaseResources(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStartLoading();
        if(data != null) {
            onReleaseResources(data);
            data = null;
        }
    }

    protected void onReleaseResources(Object object) {
        // TBA
        object = null;
    }

    private Object data;
    private Bundle args;
    private ILoaderRace iLoaderRace;
    private RaceDatabase raceDatabase;
}
/*
https://developer.android.com/reference/android/content/AsyncTaskLoader.html
 */

