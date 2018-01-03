package mcssoft.com.raceremindermvp.model.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
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
@Entity(tableName = "RACES")
public class Race {

    public Race() {
        id = "0";
        archvFlag = "N";
    }

    @Ignore
    public Race(List<String> list) {
        this.id = list.get(0);
        this.meetingId = list.get(1);
        this.raceNumber = list.get(2);
        this.raceTime = list.get(3);
        this.raceName = list.get(4);
        this.raceDistance = list.get(5);
        this.archvFlag = list.get(6);
    }

    @Ignore
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
    @PrimaryKey @NonNull
    @ColumnInfo(name = "_id") private String id;

    @ColumnInfo(name = "RaceNumber") private String raceNumber;    // e.g. "1"
    @ColumnInfo(name = "RaceTime") private String raceTime;      // e.g. "12:55"
    @ColumnInfo(name = "RaceName") private String raceName;      // e.g. "BM 60 HANDICAP"
    @ColumnInfo(name = "RaceDist") private String raceDistance;  // e.g. "1905"

    // additional.
    @ForeignKey(entity = Meeting.class, parentColumns = "id", childColumns = "MeetingId")
    @ColumnInfo(name = "MeetingId") private String meetingId;     // FK link to MEETINGS table.

    @NonNull
    @ColumnInfo(name = "ArchvFlag") private String archvFlag;
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
