package mcssoft.com.raceremindermvp.model.database;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * Class to model a Race (within a Meeting).
 *
 * Derived from: https://tatts.com/pagedata/racing/YYYY/M(M)/D(D)/<code>.xml
 * <RaceDay>
 *     <Meeting>
 *         <Race> ... </Race>
 *         . . .
 *     </Meeting>
 *     . . .
 * </RaceDay>
 */
public class Race {

    public Race() {
        id = "0";
        archvFlag = "N";
    }

    public Race(List<String> list) {
        this.id = list.get(0);
        this.meetingId = list.get(1);
        this.raceNumber = list.get(2);
        this.raceTime = list.get(3);
        this.raceName = list.get(4);
        this.raceDistance = list.get(5);
        this.archvFlag = list.get(6);
    }

    public Race(@NonNull String id, String meetingId, String raceNumber, String raceTime, String raceName, String raceDistance, @NonNull String archvFlag) {
        this.id = id;
        this.meetingId = meetingId;
        this.raceNumber = raceNumber;
        this.raceTime = raceTime;
        this.raceName = raceName;
        this.raceDistance = raceDistance;
        this.archvFlag = archvFlag;
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Getter/Setter">
    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getRaceNumber() {
        return raceNumber;
    }

    public void setRaceNumber(String raceNumber) {
        this.raceNumber = raceNumber;
    }

    public String getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(String raceTime) {
        this.raceTime = raceTime;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public String getRaceDistance() {
        return raceDistance;
    }

    public void setRaceDistance(String raceDistance) {
        this.raceDistance = raceDistance;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    @NonNull
    public String getArchvFlag() {
        return archvFlag;
    }

    public void setArchvFlag(@NonNull String archvFlag) {
        this.archvFlag = archvFlag;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private">
    // Columns for RACES table.
    private String id;

    private String raceNumber;    // e.g. "1"
    private String raceTime;      // e.g. "12:55"
    private String raceName;      // e.g. "BM 60 HANDICAP"
    private String raceDistance;  // e.g. "1905"

    // additional.
    private String meetingId;     // FK link to MEETINGS table.

    private String archvFlag;
    //</editor-fold>
}
/*
 Example
 -------
 Using: Using: https://tatts.com/pagedata/racing/2017/3/27/NR.xml

 <Race RaceNo="1"
       RaceTime="2017-03-27T12:55:00"
       RaceName="BM 60 HANDICAP"
       Distance="1905"
       RaceDisplayStatus="SELLING"
       WeatherChanged="N"
       WeatherCond="2"
       WeatherDesc="Overcast"
       TrackChanged="N"
       TrackCond="5"
       TrackDesc="Heavy">
 */
