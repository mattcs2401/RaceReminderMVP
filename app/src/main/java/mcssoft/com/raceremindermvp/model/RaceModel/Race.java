package mcssoft.com.raceremindermvp.model.RaceModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class to model the details for a single Race.
 */
public class Race implements Parcelable {

    public Race() {
        this.id = "";
        this.cityCode = "";
        this.raceCode = "";
        this.raceNum = "";
        this.raceSel = "";
        this.dateTime = "";
        this.dChgReq = "N";
        this.notified = "N";
    }

    /**
     * Initialise the Race object.
     * @param id       database row id
     * @param cityCode city code
     * @param raceCode race code
     * @param raceNum  race number
     * @param raceSel  race selection
     * @param dateTime race date/time
     */
    public Race(String id, String cityCode, String raceCode, String raceNum, String raceSel, String dateTime) {
        this.id = id;
        this.cityCode = cityCode;
        this.raceCode = raceCode;
        this.raceNum = raceNum;
        this.raceSel = raceSel;
        this.dateTime = dateTime;
        this.dChgReq = "N";
        this.notified = "N";
    }

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
        dest.writeString(dateTime);
        dest.writeString(dChgReq);
        dest.writeString(notified);
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
        this.dateTime = in.readString();
        this.dChgReq = in.readString();
        this.notified = in.readString();
    }

    public static final Parcelable.Creator<Race> CREATOR = new Parcelable.Creator<Race>() {
        @Override
        public Race createFromParcel(Parcel source) {
            return new Race(source);
        }

        @Override
        public Race[] newArray(int size) {
            return new Race[size];
        }
    };
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getdChgReq() {
        return dChgReq;
    }

    public void setdChgReq(String dChgReq) {
        this.dChgReq = dChgReq;
    }

    public String getNotified() {
        return notified;
    }

    public void setNotified(String notified) {
        this.notified = notified;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Private">
    private String id;
    private String cityCode;
    private String raceCode;
    private String raceNum;
    private String raceSel;
    private String dateTime;
    private String dChgReq;
    private String notified;
    //</editor-fold>
}
//    public static final String COLUMN_ROWID = "_id"; // Note: Has to be like this (upper case _ID ?).
//    public static final String COLUMN_CITY_CODE = "CITY_CODE";
//    public static final String COLUMN_RACE_CODE = "RACE_CODE";
//    public static final String COLUMN_RACE_NUM = "RACE_NUM";
//    public static final String COLUMN_RACE_SEL = "RACE_SEL";
//    public static final String COLUMN_DATE_TIME = "DATE_TIME";
//    // Generic field to indicate a display change is required.
//    public static final String COLUMN_D_CHG_REQ = "D_CHG_REQ";
//    // Generic field that indicates if a notification set for the record.
//    public static final String COLUMN_NOTIFIED = "NOTIFIED";
