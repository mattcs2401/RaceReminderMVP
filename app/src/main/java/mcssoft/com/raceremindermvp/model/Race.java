package mcssoft.com.raceremindermvp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "Race")
public class Race implements Parcelable {

    //<editor-fold defaultstate="collapsed" desc="Region: Constructors">
    /**
     * Initialise the Race object.
     * @param id       database row id
     * @param cityCode city code
     * @param raceCode race code
     * @param raceNum  race number
     * @param raceSel  race selection
     * @param date race date
     * @param time race time
     */
    public Race(String id, String cityCode, String raceCode, String raceNum, String raceSel, String date, String time) {
        this.id = id;
        this.cityCode = cityCode;
        this.raceCode = raceCode;
        this.raceNum = raceNum;
        this.raceSel = raceSel;
        this.date = date;
        this.time = time;
        this.dChgReq = "N";
        this.notified = "N";
        this.archive = "N";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Getter/Setter">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getRaceCode() {
        return raceCode;
    }

    public void setRaceCode(String raceCode) {
        this.raceCode = raceCode;
    }

    public String getRaceNum() {
        return raceNum;
    }

    public void setRaceNum(String raceNum) {
        this.raceNum = raceNum;
    }

    public String getRaceSel() {
        return raceSel;
    }

    public void setRaceSel(String raceSel) {
        this.raceSel = raceSel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDChgReq() {
        return dChgReq;
    }

    public void setDChgReq(String dChgReq) {
        this.dChgReq = dChgReq;
    }

    public String getNotified() {
        return notified;
    }

    public void setNotified(String notified) {
        this.notified = notified;
    }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public String getdChgReq() { return dChgReq; }

    public void setdChgReq(String dChgReq) {this.dChgReq = dChgReq; }

    public String getArchive() { return archive; }

    public void setArchive(String archive) { this.archive = archive; }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Parcelable">
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(cityCode);
        dest.writeString(raceCode);
        dest.writeString(raceNum);
        dest.writeString(raceSel);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(dChgReq);
        dest.writeString(notified);
        dest.writeString(archive);
    }

    /**
     * Retrieving Race data from Parcel object. This constructor is invoked by the method
     * createFromParcel(Parcel source) of the object CREATOR.
     **/
    public Race(Parcel in) {
        this.id = in.readString();
        this.cityCode = in.readString();
        this.raceCode = in.readString();
        this.raceNum = in.readString();
        this.raceSel = in.readString();
        this.date = in.readString();
        this.time = in.readString();
        this.dChgReq = in.readString();
        this.notified = in.readString();
        this.archive = in.readString();
    }

    public static final Parcelable.Creator<mcssoft.com.raceremindermvp.model.Race> CREATOR = new Parcelable.Creator<mcssoft.com.raceremindermvp.model.Race>() {
        @Override
        public mcssoft.com.raceremindermvp.model.Race createFromParcel(Parcel source) {
            return new mcssoft.com.raceremindermvp.model.Race(source);
        }

        @Override
        public mcssoft.com.raceremindermvp.model.Race[] newArray(int size) {
            return new mcssoft.com.raceremindermvp.model.Race[size];
        }
    };
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private">
    @PrimaryKey @NonNull
    @ColumnInfo(name = "_id") private String id;

    @ColumnInfo(name = "CityCode") private String cityCode;
    @ColumnInfo(name = "RaceCode") private String raceCode;
    @ColumnInfo(name = "RaceNum") private String raceNum;
    @ColumnInfo(name = "RaceSel") private String raceSel;
    @ColumnInfo(name = "Date") private String date;
    @ColumnInfo(name = "Time") private String time;
    @ColumnInfo(name = "DChgReq") private String dChgReq;
    @ColumnInfo(name = "Notified") private String notified;
    @ColumnInfo(name = "Archive") private String archive;
    //</editor-fold>
}
