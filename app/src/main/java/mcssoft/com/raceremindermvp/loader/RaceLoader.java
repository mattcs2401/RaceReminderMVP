package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import mcssoft.com.raceremindermvp.database.RaceDatabase;

public class RaceLoader extends AsyncTaskLoader<Cursor> {

    public RaceLoader(Context context, RaceDatabase raceDatabase) {
        super(context);
        this.context = context;
        this.raceDatabase = raceDatabase;
    }

    @Override
    public Cursor loadInBackground() {
        // TODO - something to differentiate operation type, e.g. update, insert, select etc.
        //        maybe some sort of loader manager?
        return raceDatabase.getInstance(context).getRaceDAO().selectAll();
//        lRaces = raceDatabase.getInstance(context).getRaceDAO().selectAll();
//        if(lRaces == null || lRaces.getCount() == 0) {
//
//        }
//        return lRaces;
    }

    @Override
    protected void onStartLoading() {
        if(lRaces != null) {
            deliverResult(lRaces);
        }
        if(takeContentChanged() || lRaces == null) {
            forceLoad();
        }
    }

    @Override
    public void deliverResult(Cursor data) {
        super.deliverResult(data);
    }

    private Cursor lRaces;
    private Context context;
    private RaceDatabase raceDatabase;
}
