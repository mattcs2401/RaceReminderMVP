package mcssoft.com.raceremindermvp.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.database.Database;
import mcssoft.com.raceremindermvp.database.Schema;
import mcssoft.com.raceremindermvp.model.Race;

public class RaceTaskLoader extends AsyncTaskLoader<List<Race>> {

    public RaceTaskLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public List<Race> loadInBackground() {
        Database database = new Database(context);
        Cursor cursor = database.getRecords(Schema.RACE_DETAILS, null, null, null);
        database.destroy();

//        return loadDummyData();
        return convertData(cursor);
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
    public void deliverResult(List<Race> data) {
        super.deliverResult(data);
        String bp = "";
    }

    private List<Race> convertData(Cursor cursor) {
        cursor.moveToFirst();
        String bp = "";
        return null;
    }

    private List<Race> loadDummyData() {
        lRaces = new ArrayList<>();
        Race r1 = new Race("1", "B", "R", "1", "10", "01/01/2017 12:00");
        Race r2 = new Race("2", "B", "R", "2", "11", "01/01/2017 12:40");
        lRaces.add(r1);
        lRaces.add(r2);
        return lRaces;
    }

    private Context context;
    private List<Race> lRaces;
}
