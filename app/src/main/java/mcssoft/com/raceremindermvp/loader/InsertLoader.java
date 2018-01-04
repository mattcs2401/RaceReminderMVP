package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import java.util.List;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.utility.Resources;

public class InsertLoader extends AsyncTaskLoader {

//    public InsertLoader(Context context, RaceDatabase raceDatabase, Object data) {
//        super(context);
//        this.context = context;
//        this.raceDatabase = raceDatabase;
//        this.data = data;
//    }

    public InsertLoader(Context context, RaceDatabase raceDatabase, Object data, Bundle args) {
        super(context);
        this.context = context;
        this.raceDatabase = raceDatabase;
        this.args = args;
    }


    @Override
    public Object loadInBackground() {
        int id = args.getInt("key");
        if(id == Resources.getInstance(context).getInteger(R.integer.insert_for_meetings)) {
            List<Meeting> meetings = (List) data;
            raceDatabase.getInstance(context).getMeetingDAO().insertAll(meetings);
        }
//        if(id == -1) {
//            list = raceDatabase.getInstance(context).getMeetingDAO().selectAll("N");
//        } else {
//            list = raceDatabase.getInstance(context).getMeetingDAO().selectMeeting(id);
//        }
//        if(list == null || list.size() == 0) {
//            // TBA - have we already captured this occurance ?
//        }
        return list;
    }

    @Override
    protected void onStartLoading() {
//        if(list != null) {
//            deliverResult(list);
//        }
//        if(takeContentChanged() || list == null) {
//            forceLoad();
//        }
    }

    @Override
    public void deliverResult(Object data) {
        super.deliverResult(data);
    }

    private int id;                    // meeting id (if provided).
    private List list;                 // processing result.
    private Object data;
    private Bundle args;
    private Context context;           // current context;
    private RaceDatabase raceDatabase; // reference to RaceDatabase.
}
