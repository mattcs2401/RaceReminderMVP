package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.utility.OpType;

import static mcssoft.com.raceremindermvp.utility.OpType.MType.COUNT_MEETINGS;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.DELETE_MEETING;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.DELETE_MEETINGS;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.INSERT_MEETINGS;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.SELECT_MEETINGS;

/**
 * Utility class that provides the background thread for the Room database object 'raceDatabase'.
 */
public class MeetingLoader extends AsyncTaskLoader<Object> {

    public MeetingLoader(Context context, RaceDatabase raceDatabase, Bundle bundle) {
        super(context);
        this.bundle = bundle;
        this.raceDatabase = raceDatabase;
        ButterKnife.bind(this, new View(context)); // a bit of a hack but seems to work.
    }

    @Override
    public Object loadInBackground() {
        Object object = null;
        switch (bundle.getInt(bundle_key)) {
            case COUNT_MEETINGS:
                object = raceDatabase.getMeetingDAO().getMeetingsCount("N");
                break;
            case INSERT_MEETINGS:
                List<Meeting> lMeeting = bundle.getParcelableArrayList(bundle_data_key);
                object = raceDatabase.getMeetingDAO().insertMeetings(lMeeting);
                break;
            case SELECT_MEETINGS:
                object = raceDatabase.getMeetingDAO().getMeetings("N");
                break;
            case DELETE_MEETINGS:
                object = raceDatabase.getMeetingDAO().deleteMeetings();
                break;
            }
        return object;
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
        if (theResult != null) {
            // If we currently have a result available, deliver it now.
            deliverResult(theResult);
        }

        if (takeContentChanged() || theResult == null) {
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

    @OpType.MType
    int opType;

    @BindString(R.string.bundle_key) String bundle_key;
    @BindString(R.string.bundle_data_key) String bundle_data_key;
}
/*
Example:
https://developer.android.com/reference/android/content/AsyncTaskLoader.html
 */