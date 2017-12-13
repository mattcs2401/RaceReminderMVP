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
        Race r1 = new Race("1", "B", "R", "1", "01", "01/01/2017 12:00");
        Race r2 = new Race("2", "B", "R", "2", "02", "01/01/2017 12:30");
        Race r3 = new Race("3", "B", "R", "3", "03", "01/01/2017 13:00");
        Race r4 = new Race("4", "B", "R", "4", "04", "01/01/2017 13:30");
        Race r5 = new Race("5", "B", "R", "5", "05", "01/01/2017 14:00");
        Race r6 = new Race("6", "B", "R", "6", "06", "01/01/2017 14:30");
        Race r7 = new Race("7", "B", "R", "7", "07", "01/01/2017 15:00");
        Race r8 = new Race("8", "B", "R", "8", "08", "01/01/2017 15:30");
        Race r9 = new Race("9", "B", "R", "9", "09", "01/01/2017 16:00");
        Race r10 = new Race("10", "B", "R", "10", "10", "01/01/2017 16:30");
        Race r11 = new Race("11", "B", "R", "11", "11", "01/01/2017 17:00");
        Race r12 = new Race("12", "B", "R", "12", "12", "01/01/2017 17:30");

        lRaces.add(r1);
        lRaces.add(r2);
        lRaces.add(r3);
        lRaces.add(r4);
        lRaces.add(r5);
        lRaces.add(r6);
        lRaces.add(r7);
        lRaces.add(r8);
        lRaces.add(r9);
        lRaces.add(r10);
        lRaces.add(r11);
        lRaces.add(r12);
        return lRaces;
    }

    private List<Race> lRaces;
    private Context context;
    private RaceDatabase raceDatabase;
}
