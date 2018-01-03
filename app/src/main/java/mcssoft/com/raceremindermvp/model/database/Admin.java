package mcssoft.com.raceremindermvp.model.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Utility class to model the ADMIN table.
 */
@Entity(tableName = "ADMIN")
public class Admin {

    //<editor-fold defaultstate="collapsed" desc="Region: Constructors">
    public Admin() {
        id = "0";
        meetingId = "0";
        hasRaces = "N";
        archvFlag = "N";
    }

    @Ignore
    public Admin(@NonNull String id, @NonNull String meetingId, @NonNull String hasRaces, @NonNull String archvFlag) {
        this.id = id;
        this.meetingId = meetingId;
        this.hasRaces = hasRaces;
        this.archvFlag = archvFlag;
    }

    @Ignore
    public Admin(List<String> list) {
        id = list.get(0);
        meetingId = list.get(1);
        hasRaces = list.get(2);
        archvFlag = list.get(3);
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

    @NonNull
    public String getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(@NonNull String meetingId) {
        this.meetingId = meetingId;
    }

    @NonNull
    public String getHasRaces() {
        return hasRaces;
    }

    public void setHasRaces(@NonNull String hasRaces) {
        this.hasRaces = hasRaces;
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
    // Columns for ADMIN table.
    @PrimaryKey @NonNull
    @ColumnInfo(name = "_id") private String id;

    @NonNull
    @ColumnInfo(name = "MeetingId")   private String meetingId;      // e.g. "1224999936"
    @NonNull
    @ColumnInfo(name = "HasRaces")   private String hasRaces;        // e.g. "N" or "Y"

    @NonNull
    @ColumnInfo(name = "ArchvFlag")   private String archvFlag;      // e.g. "N" or "Y"
    //</editor-fold>
}
