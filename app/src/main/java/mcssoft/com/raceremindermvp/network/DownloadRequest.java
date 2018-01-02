package mcssoft.com.raceremindermvp.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.utility.Resources;
import mcssoft.com.raceremindermvp.utility.XmlParser;

/**
 * Custom Volley.Request<T> class
 * @param <T>
 */
public class DownloadRequest<T> extends Request<List> {

    public DownloadRequest(int method, String url, Context context, Response.Listener listener,
                           Response.ErrorListener errorListener, String tableName) {
        super(method, url, errorListener);
        this.context = context;
        this.tableName = tableName;
        this.listener = listener;
        this.errorListener = errorListener;
    }

    // From the doco, runs on a background worker thread.
    @Override
    protected Response<List> parseNetworkResponse(NetworkResponse response) {
        List theResult = null;         // main list of result objects from parsing the Xml.
        XmlParser parser = null;

        InputStream instream = new ByteArrayInputStream(response.data);

        try {
            parser = new XmlParser(context, instream);
            // Parse the response into Meeting, Race or Runner objects.
            // TODO - remove hard coded table names.
            switch(tableName) {
                case "Meetings":
                    theResult = parser.parse(Resources.getInstance(context)
                            .getString(R.string.meetings_xml_tag));
                    break;
                case "RACES":
                    theResult = parser.parse(Resources.getInstance(context)
                            .getString(R.string.races_xml_tag));
                    break;
                case "RUNNERS":
                    theResult = parser.parse(Resources.getInstance(context)
                            .getString(R.string.runners_xml_tag));
                    break;
            }
            // Write the results to the database (if don't already exist).
//            checkOrInsert(theResult, tableName);

        } catch(Exception ex) {
            Log.d(this.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            return Response.success(theResult, null);
        }
    }

    @Override
    protected void deliverResponse(List response) {
        listener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        errorListener.onErrorResponse(error);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    /**
     * Insert objects into the database (if don't already exist).
     * @param theList The list of objects (Meeting or Race or Runner).
     * @param output An indicator as to what table to write to.
     */
//    private void checkOrInsert(List theList, String output) {
//        DatabaseOperations dbOper = new DatabaseOperations(context);
//        switch (output) {
//            case SchemaConstants.MEETINGS_TABLE:
//                checkOrInsertMeetings(theList);
//                break;
//            case SchemaConstants.RACES_TABLE:
//                checkOrInsertRaces(theList);
//                break;
//            case SchemaConstants.RUNNERS_TABLE:
//                checkOrInsertRunners(theList);
//                break;
//        }
//    }

    /**
     * Insert Meeting objects into the database (if don't already exist).
     * @param meetingList The list of Meeting objects.
     */
//    private void checkOrInsertMeetings(List meetingList) {
//        Meeting meeting = null;
//        DatabaseOperations dbOper = new DatabaseOperations(context);
//
//        for(Object object : meetingList) {
//            // this is a new Meeting record.
//            meeting = ((Meeting) object);
//            if (!dbOper.checkRecordsExist(SchemaConstants.MEETINGS_TABLE,
//                    SchemaConstants.MEETING_ID, meeting.getMeetingId())) {
//                dbOper.insertMeetingRecord(meeting);
//            }
//        }
//    }

    /**
     * Insert Race objects into the database (if don't already exist).
     * @param raceList The list of Race objects.
     */
//    private void checkOrInsertRaces(List raceList) {
//        DatabaseOperations dbOper = new DatabaseOperations(context);
//
//        if(!dbOper.checkRecordsExist(SchemaConstants.RACES_TABLE, SchemaConstants.RACE_MEETING_ID,
//                ((Race) raceList.get(0)).getMeetingId())) {
//
//            for(Object object : raceList) {
//                Race race = ((Race) object);
//                dbOper.insertRaceRecord(race);
//            }
//        }
//    }

    /**
     * Insert Runner objects into the database (if don't already exist).
     * @param list The list of Runner objects.
     */
//    private void checkOrInsertRunners(List list) {
//        DatabaseOperations dbOper = new DatabaseOperations(context);
//        for(Object object : list) {
//            Runner runner = ((Runner) object);
//            // TODO - insert Runner objects.
////            if(!dbOper.checkRecordsExist(SchemaConstants.RUNNERS_TABLE,
////                    SchemaConstants.RACE_MEETING_ID, race.getMeetingId())) {
////                dbOper.insertRaceRecord(race);
////            }
//        }
//    }

    /**
     * Write Meeting identifier into Race objects.
     * @param meetingId The Meeting identifier.
     * @param theResult The list of Race objects.
     * @return The updated list of Race objects.
     */
//    private List mergeMeetingId(String meetingId, List theResult) {
//        for(Object object : theResult) {
//            Race race = (Race) object;
//            race.setMeetingId(meetingId);
//        }
//        return theResult;
//    }

    /**
     * Create a Meeting object that only contains weather info.
     * @param theList A list of the info to use.
     * @return A Meeting object.
     */
    private Meeting createMeetingWeather(List theList) {
//        Meeting meeting = new Meeting();
//        //[0]-meeting id, [1]-track desc, [2]-track rating, [3]-weather desc.
//        meeting.setMeetingId((String) theList.get(0));
//        meeting.setTrackDescription((String) theList.get(1));
//        meeting.setTrackRating((String) theList.get(2));
//        meeting.setTrackWeather((String) theList.get(3));
//        return meeting;
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private vars">
    private Context context;                          // context for database operations.
    private String tableName;                         // the affected table.
    private Response.Listener<List> listener;         // non-error listener callback.
    private Response.ErrorListener errorListener;     // error listener callback.
    //</editor-fold>
}
