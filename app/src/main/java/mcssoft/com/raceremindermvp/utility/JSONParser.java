package mcssoft.com.raceremindermvp.utility;

import android.content.Context;
import android.view.View;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;

import static mcssoft.com.raceremindermvp.utility.DbType.TName.MEETINGS;
import static mcssoft.com.raceremindermvp.utility.DbType.TName.RACES;
import static mcssoft.com.raceremindermvp.utility.DbType.TName.RUNNERS;

public class JSONParser {

    public JSONParser(Context context, InputStream in) throws UnsupportedEncodingException { //throws IOException {
        this.context = context;
        haveWeather = false;
        ButterKnife.bind(this, new View(context));
        reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

          // throws "Not a JSON object"
//        JsonParser parser = new JsonParser();
//        JsonArray array = parser.parse(reader).getAsJsonArray();

        String bp = "";
    }

    /**
     * Parse the feed xml into a list of objects that represent the feed elements.
     * @param element The part of the feed to read, e.g. Meeting, Race or Runner elements.
     * @return A list of objects representing the feed elements.
     * @throws IOException
     */
    public List parse(String element) throws IOException {
        List list = null;
        switch(element) {
            case MEETINGS:
                list = parseForMeetings();
                break;
            case RACES:
                list = parseForRaces();
                break;
            case RUNNERS:
                list = parseForRunners();
                break;
        }
        return list;
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Meeting">
    private List parseForMeetings() throws IOException {
        List<Meeting> lMeeting = new ArrayList<Meeting>();

        handleObject(reader);

        return lMeeting;
    }

    /**
     * Handle an Object. Consume the first token which is BEGIN_OBJECT. Within
     * the Object there could be array or non array tokens. We write handler
     * methods for both. Noe the peek() method. It is used to find out the type
     * of the next token without actually consuming it.
     *
     * @param reader
     * @throws IOException
     */
    private static void handleObject(JsonReader reader) throws IOException
    {
        reader.beginObject();
        while (reader.hasNext()) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.BEGIN_ARRAY))
                handleArray(reader);
            else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
                return;
            } else
                handleNonArrayToken(reader, token);
        }

    }

    /**
     * Handle a json array. The first token would be JsonToken.BEGIN_ARRAY.
     * Arrays may contain objects or primitives.
     *
     * @param reader
     * @throws IOException
     */
    public static void handleArray(JsonReader reader) throws IOException
    {
        reader.beginArray();
        while (true) {
            JsonToken token = reader.peek();
            if (token.equals(JsonToken.END_ARRAY)) {
                reader.endArray();
                break;
            } else if (token.equals(JsonToken.BEGIN_OBJECT)) {
                handleObject(reader);
            } else if (token.equals(JsonToken.END_OBJECT)) {
                reader.endObject();
            } else
                handleNonArrayToken(reader, token);
        }
    }

    /**
     * Handle non array non object tokens
     *
     * @param reader
     * @param token
     * @throws IOException
     */
    public static void handleNonArrayToken(JsonReader reader, JsonToken token) throws IOException
    {
        if (token.equals(JsonToken.NAME))
            System.out.println(reader.nextName());
        else if (token.equals(JsonToken.STRING))
            System.out.println(reader.nextString());
        else if (token.equals(JsonToken.NUMBER))
            System.out.println(reader.nextDouble());
        else
            reader.skipValue();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Meeting">
    private List parseForRaces() {
        List<Race> lRace = new ArrayList<Race>();

        return lRace;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Meeting">
    private List parseForRunners() {
        // TBA
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Variables">
    private Context context;           // for DateTime singleton.
    private JsonReader reader;
    private  boolean haveWeather;      // flag, weather details retieved.

    @BindString(R.string.meeting) String meeting;               // Meeting
    @BindString(R.string.race_day_date) String race_day_date;   // RaceDayDate
    @BindString(R.string.abandoned) String abandoned;           // Abandoned
    @BindString(R.string.venue_name) String venue_name;         // VenueName
    @BindString(R.string.hi_race_no) String hi_race_no;         // HiRaceNo
    @BindString(R.string.meeting_code) String meeting_code;     // MeetingCode
    @BindString(R.string.meeting_id) String meeting_id;         // MtgId
    @BindString(R.string.weather_desc) String weather_desc;     // WeatherDesc
    @BindString(R.string.track_rating) String track_rating;     // TrackRating
    @BindString(R.string.track_desc) String track_desc;         // TrackDesc

    @BindString(R.string.race_no) String race_no;               // RaceNo
    @BindString(R.string.race_time) String race_time;           // RaceTime
    @BindString(R.string.race_name) String race_name;           // RaceName
    @BindString(R.string.race_distance) String race_distance;   // Distance
    @BindString(R.string.race) String race;                     // Race
    @BindString(R.string.race_day) String race_day;             // RaceDay
    //</editor-fold>
}
