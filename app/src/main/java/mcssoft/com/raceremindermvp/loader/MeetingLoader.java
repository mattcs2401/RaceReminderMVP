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
            lMeetings = loadDummyData();
        }
        return lMeetings;    }

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

    private List<Meeting> loadDummyData() {
        lMeetings = new ArrayList<>();
//        Race r1 = new Race("1", "B", "R", "1", "01", "01/01/2017","12:00");
//        Race r2 = new Race("2", "B", "R", "2", "02", "01/01/2017","12:30");
//        Race r3 = new Race("3", "B", "R", "3", "03", "01/01/2017","13:00");
//        Race r4 = new Race("4", "B", "R", "4", "04", "01/01/2017","13:30");
//        Race r5 = new Race("5", "B", "R", "5", "05", "01/01/2017","14:00");
//        Race r6 = new Race("6", "B", "R", "6", "06", "01/01/2017","14:30");
//        Race r7 = new Race("7", "B", "R", "7", "07", "01/01/2017","15:00");
//        Race r8 = new Race("8", "B", "R", "8", "08", "01/01/2017","15:30");
//        Race r9 = new Race("9", "B", "R", "9", "09", "01/01/2017","16:00");
//        Race r10 = new Race("10", "B", "R", "10", "10", "01/01/2017","16:30");
//        Race r11 = new Race("11", "B", "R", "11", "11", "01/01/2017","17:00");
//        Race r12 = new Race("12", "B", "R", "12", "12", "01/01/2017","17:30");

//        lMeetings.add(r1);
//        lMeetings.add(r2);
//        lMeetings.add(r3);
//        lMeetings.add(r4);
//        lMeetings.add(r5);
//        lMeetings.add(r6);
//        lMeetings.add(r7);
//        lMeetings.add(r8);
//        lMeetings.add(r9);
//        lMeetings.add(r10);
//        lMeetings.add(r11);
//        lMeetings.add(r12);
        return lMeetings;
    }

    private List<Meeting> lMeetings;
    private Context context;
    private RaceDatabase raceDatabase;
}
