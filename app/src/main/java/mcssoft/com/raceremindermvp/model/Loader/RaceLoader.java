package mcssoft.com.raceremindermvp.model.Loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.model.Database.Database;
import mcssoft.com.raceremindermvp.model.Database.Schema;
import mcssoft.com.raceremindermvp.model.RaceModel.Race;

public class RaceLoader extends AsyncTaskLoader<List<Race>> {

    public RaceLoader(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public List<Race> loadInBackground() {
        Database database = new Database(context);
        Cursor cursor = database.getRecords(Schema.RACE_DETAILS, null, null, null);
        database.destroy();

        return loadDummyData();
    }

    @Override
    protected void onStartLoading() {
        if(lRace != null) {
            deliverResult(lRace);
        }
        if(takeContentChanged() || lRace == null) {
            forceLoad();
        }
    }

//    @Override
//    public void deliverResult(List<Race> data) {
//        super.deliverResult(data);
//    }


    private List<Race> loadDummyData() {
        lRace = new ArrayList<>();
        Race r1 = new Race();
        Race r2 = new Race();
        lRace.add(r1);
        lRace.add(r2);
        return lRace;
    }

    private Context context;
    private List<Race> lRace;
}
