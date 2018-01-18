package mcssoft.com.raceremindermvp.model.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Class to model a (race) Meeting.
 *
 * Derived from: https://tatts.com/pagedata/racing/YYYY/M(M)/D(D)/RaceDay.xml
 * <RaceDay>
 *     <Meeting> ... </Meeting>
 *     . . .
 * </RaceDay>
 */
@Entity(tableName = "MEETINGS")
public class Meeting {

    //<editor-fold defaultstate="collapsed" desc="Region: Constructors">
    public Meeting() {
        id = "0";
        archvFlag = "N";
    }

    @Ignore
    public Meeting(@NonNull String id, String meetingId, String abandoned, String venueName, String hiRaceNo, String meetingCode, String meetingDate,
                   @Nullable String trackDesc, @Nullable String trackRating, @Nullable String weatherDesc, String archvFlag) {
        this.id = id;
        this.meetingId = meetingId;
        this.abandoned = abandoned;
        this.venueName = venueName;
        this.hiRaceNo = hiRaceNo;
        this.meetingCode = meetingCode;
        this.meetingDate = meetingDate;
        this.trackDesc = trackDesc;
        this.trackRating = trackRating;
        this.weatherDesc = weatherDesc;
        this.archvFlag = archvFlag;

        // Note: 'id' and 'archvFlag' are not part of the XML.
    }

    @Ignore
    public Meeting(List<String> list) {
        this.id = list.get(0);
        this.meetingId = list.get(0);
        this.abandoned = list.get(0);
        this.venueName = list.get(0);
        this.hiRaceNo = list.get(0);
        this.meetingCode = list.get(0);
        this.meetingDate = list.get(0);
        this.trackDesc = list.get(0);
        this.trackRating = list.get(0);
        this.weatherDesc = list.get(0);
        this.archvFlag = list.get(0);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Getter/Setter">
    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(String meetingId) {
        this.meetingId = meetingId;
    }

    public String getAbandoned() {
        return abandoned;
    }

    public void setAbandoned(String abandoned) {
        this.abandoned = abandoned;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getHiRaceNo() {
        return hiRaceNo;
    }

    public void setHiRaceNo(String hiRaceNo) {
        this.hiRaceNo = hiRaceNo;
    }

    public String getMeetingCode() {
        return meetingCode;
    }

    public void setMeetingCode(String meetingCode) {
        this.meetingCode = meetingCode;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getTrackDesc() {
        return trackDesc;
    }

    public void setTrackDesc(String trackDesc) {
        this.trackDesc = trackDesc;
    }

    public String getTrackRating() {
        return trackRating;
    }

    public void setTrackRating(String trackRating) {
        this.trackRating = trackRating;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

    public void setWeatherDesc(String weatherDesc) {
        this.weatherDesc = weatherDesc;
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
    // Columns for MEETINGS table.
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "_id") private String id;

    // From RaceDay.xml
    @ColumnInfo(name = "MeetingId")   private String meetingId;      // e.g. "1224999936"
    @ColumnInfo(name = "Abandoned")   private String abandoned;      // e.g. "N"
    @ColumnInfo(name = "VenueName")   private String venueName;      // e.g. "Goulburn"
    @ColumnInfo(name = "HiRaceNo")    private String hiRaceNo;         // e.g "7"
    @ColumnInfo(name = "MeetingCode") private String meetingCode;    // e.g. "NR"

    // Derived from <RaceDay RaceDayDate=.../>
    @ColumnInfo(name = "MeetingDate") private String meetingDate;    // e.g. "YYYY-M(M)-D(D)"

    // From <meeting_code>.xml
    // TBA - do we really need this ?
    @ColumnInfo(name = "TrackDesc")   private String trackDesc;      // e.g. "Soft"
    @ColumnInfo(name = "TrackRating") private String trackRating;    // e.g. "5"
    @ColumnInfo(name = "weatherDesc") private String weatherDesc;    // e.g. "Overcast"

    @NonNull
    @ColumnInfo(name = "ArchvFlag")   private String archvFlag;      // e.g. "N"
    //</editor-fold>
}

