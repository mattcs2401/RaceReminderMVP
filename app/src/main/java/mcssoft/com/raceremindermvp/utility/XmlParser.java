package mcssoft.com.raceremindermvp.utility;

import android.content.Context;
import android.util.Xml;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;
import mcssoft.com.raceremindermvp.model.database.Runner;

import static mcssoft.com.raceremindermvp.utility.DbType.TName.MEETINGS;
import static mcssoft.com.raceremindermvp.utility.DbType.TName.RACES;
import static mcssoft.com.raceremindermvp.utility.DbType.TName.RUNNERS;
import static org.xmlpull.v1.XmlPullParser.START_TAG;

public class XmlParser {

    public XmlParser(Context context, InputStream inputStream) throws XmlPullParserException, IOException {
        this.context = context;
        haveWeather = false;
        nameSpace = null;
        parser = Xml.newPullParser();
        parser.setInput(inputStream, null);
        parser.nextTag();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        ButterKnife.bind(this, new View(context));
    }

    /**
     * Parse the feed xml into a list of objects that represent the feed elements.
     * @param element The part of the feed to read, e.g. Meeting, Race or Runner elements.
     * @return A list of objects representing the feed elements.
     * @throws XmlPullParserException
     * @throws IOException
     */
    public List parse(String element) throws XmlPullParserException, IOException {
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
    /**
     * Parse the Xml for Meeting information.
     * Note: Based on https://tatts.com/pagedata/racing/YYYY/M(M)/D(D)/RaceDay.xml
     * @return A list of Meeting objects.
     * @throws XmlPullParserException
     * @throws IOException
     */
    private List parseForMeetings() throws XmlPullParserException, IOException {
        // TODO - may not be efficient but seems to work.
        List entries = new ArrayList();
        parser.require(START_TAG, nameSpace, race_day);

        String date = parser.getAttributeValue(nameSpace,race_day_date);

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != START_TAG) {
                continue;
            }
            String name = parser.getName();
            if (name.equals(meeting)) {
                entries.add(readMeeting(date));
                haveWeather = false;
            } else if(name.equals(race) && !haveWeather) {
                entries = readMeetingWeather(entries);
                haveWeather = true;
            }
            else {
                skip();
            }
        }
        return entries;
    }

    /**
     * Read Meeting infor from the Xml.
     * @param date A derived value from the RaceDayDate attribute of the <RaceDay></RaceDay> element.
     * @return A Meeting object.
     */
    private Meeting readMeeting(String date) {
        Meeting meeting = new Meeting();
        if(date != null) {
            // // date format YYYY-MM-DDT00:00:00 (only want date part).
            meeting.setMeetingDate((date.split("T"))[0]);
            meeting.setAbandoned(parser.getAttributeValue(nameSpace, abandoned));
            meeting.setVenueName(parser.getAttributeValue(nameSpace, venue_name));
            meeting.setHiRaceNo(parser.getAttributeValue(nameSpace, hi_race_no));
            meeting.setMeetingCode(parser.getAttributeValue(nameSpace, meeting_code));
            meeting.setMeetingId(parser.getAttributeValue(nameSpace, meeting_id));
        }
        return meeting;
    }

    /**
     * Add in the weather information for a Meeting.
     * @param list A list of all the Meetings currently parsed.
     * @return The list of Meetings with weather details appended to the last Meeting in the list.
     */
    private List readMeetingWeather(List list) {
        // Note: weather details are actually part of the <Race> element and same for each Race of
        //       that Meeting..

        // List entry we want is the last one (i.e. the most recently parsed).
        Meeting meeting = (Meeting) list.get(list.size() - 1);

        meeting.setWeatherDesc(parser.getAttributeValue(nameSpace, weather_desc));
        meeting.setTrackRating(parser.getAttributeValue(nameSpace, track_rating));
        meeting.setTrackDesc(parser.getAttributeValue(nameSpace, track_desc));
        return list;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Races">
    /**
     * Parse the Xml for Race information.
     * Note: Based on https://tatts.com/pagedata/racing/YYYY/M(M)/D(D)/<racecode>.xml
     * @return A list (primarily) of Race objects. [0]-weather (as Meeting), [1]-nn Race objects.
     * @throws XmlPullParserException
     * @throws IOException
     */
    private List parseForRaces() throws XmlPullParserException, IOException {
        // TODO - how do we optimise this ?
        String meetingId = null;
        List entries = new ArrayList();;
//        parser.require(START_TAG, nameSpace, race_day);

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.getEventType() != START_TAG) {
                continue;
            }
            String name = parser.getName();
            if(name.equals(meeting) && meetingId == null) {
                // get the Meeting ID.
                meetingId = parser.getAttributeValue(nameSpace, meeting_id);
            }
            if(name.equals(race)) {
                entries.add(readRace(meetingId));
            } else if (name.equals("Tipster")) {
                // nothing we want after this (ATT)
                break;
            } else {
//                skip();
            }
        }
        return entries;
    }

    /**
     * Read Race info from the Xml.
     * @return A Race object.
     */
    private Race readRace(String meetingId) {
        Race race = new Race();
        race.setMeetingId(meetingId);
        race.setRaceNumber(parser.getAttributeValue(nameSpace,race_no));
        DateTime dt = new DateTime(context);
        race.setRaceTime(dt.getTimeComponent(parser.getAttributeValue(nameSpace,race_time)));
        race.setRaceName(parser.getAttributeValue(nameSpace,race_name));
        race.setRaceDistance(parser.getAttributeValue(nameSpace,race_distance) + "m");
        return race;
    }
    ///</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Runners">
    /**
     * Parse the Xml for Runner information.
     * Note: Based on https://tatts.com/pagedata/racing/YYYY/M(M)/D(D)/<racecode><racenum>.xml
     * @return A list of Runner objects.
     * @throws XmlPullParserException
     * @throws IOException
     */
    private List parseForRunners() throws XmlPullParserException, IOException {
        List runners = new ArrayList();
        // TODO - parse for Runners
        return runners;
    }

    /**
     * Read Runner info from the Xml.
     * @return A Runner object.
     */
    private Runner readRunner() {
        Runner runner = new Runner();
        // TODO - read Runner.
        return runner;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    /**
     * Ignore what we don't want.
     * @throws XmlPullParserException
     * @throws IOException
     */
    private void skip() throws XmlPullParserException, IOException {
        if (parser.getEventType() != START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        String name;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case START_TAG:
                    depth++;
                    break;
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Variables">
    private Context context;           // for DateTime singleton.
    private String nameSpace;
    private XmlPullParser parser;
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
