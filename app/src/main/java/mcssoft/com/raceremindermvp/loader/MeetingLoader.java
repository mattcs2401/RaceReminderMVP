package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;

public class MeetingLoader extends AsyncTaskLoader<List<Meeting>> {

    public MeetingLoader(Context context, RaceDatabase raceDatabase) {
        super(context);
        this.context = context;
        this.raceDatabase = raceDatabase;
    }

    @Override
    public List<Meeting> loadInBackground() {
        // TODO - something to differentiate operation type, e.g. update, insert, select etc.
        //        maybe some sort of loader manager?
        lMeetings = raceDatabase.getInstance(context).getMeetingDAO().selectAll("N");
        if(lMeetings == null || lMeetings.size() == 0) {
            // TBA - have we already captured this occurance ?
        }
        return lMeetings;
    }

    @Override
    protected void onStartLoading() {
        if(lMeetings != null) {
            deliverResult(lMeetings);
        }
        if(takeContentChanged() || lMeetings == null) {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(List<Meeting> data) {
        super.deliverResult(data);
    }

    private List<Meeting> lMeetings;
    private Context context;
    private RaceDatabase raceDatabase;
}
