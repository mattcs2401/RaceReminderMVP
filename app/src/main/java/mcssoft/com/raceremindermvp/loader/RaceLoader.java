package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.model.Race;

public class RaceLoader extends AsyncTaskLoader<List<Race>> {

    public RaceLoader(Context context, RaceDatabase raceDatabase) {
        super(context);
        this.context = context;
        this.raceDatabase = raceDatabase;
    }

    @Override
    public List<Race> loadInBackground() {
        // TODO - something to differentiate operation type, e.g. update, insert, select etc.
        //        maybe some sort of loader manager?
        lRaces = raceDatabase.getInstance(context).getRaceDAO().selectAll();
        if(lRaces == null || lRaces.size() == 0) {
            lRaces = loadDummyData();
        }
        return lRaces;    }

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
    public void deliverResult(List<Race> data) {
        super.deliverResult(data);
    }

    private List<Race> loadDummyData() {
        lRaces = new ArrayList<>();
        Race r1 = new Race("1", "B", "R", "1", "10", "01/01/2017 12:00");
        Race r2 = new Race("2", "B", "R", "2", "11", "01/01/2017 12:40");
        lRaces.add(r1);
        lRaces.add(r2);
        return lRaces;
    }

    private List<Race> lRaces;
    private Context context;
    private RaceDatabase raceDatabase;
}
