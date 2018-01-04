package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import mcssoft.com.raceremindermvp.database.RaceDatabase;

public class SelectLoader extends AsyncTaskLoader<List> {

    public SelectLoader(Context context, RaceDatabase raceDatabase) {
        super(context);
        this.context = context;
        this.raceDatabase = raceDatabase;
        id = -1;
    }

    public SelectLoader(Context context, RaceDatabase raceDatabase, int id) {
        super(context);
        this.context = context;
        this.raceDatabase = raceDatabase;
        this.id = id;
    }

    @Override
    public List loadInBackground() {
        if(id == -1) {
            list = raceDatabase.getInstance(context).getMeetingDAO().selectAll("N");
        } else {
            list = raceDatabase.getInstance(context).getMeetingDAO().selectMeeting(id);
        }
        if(list == null || list.size() == 0) {
            // TBA - have we already captured this occurance ?
        }
        return list;
    }

    @Override
    protected void onStartLoading() {
        if(list != null) {
            deliverResult(list);
        }
        if(takeContentChanged() || list == null) {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(List data) {
        super.deliverResult(data);
    }

    private int id;                    // meeting id (if provided).
    private List list;                 // processing result.
    private Context context;           // current context;
    private RaceDatabase raceDatabase; // reference to RaceDatabase.
}
