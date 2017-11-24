package mcssoft.com.raceremindermvp.model.RaceModel;

public class Race {

    public Race() {}

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

    private String id;
    private String cityCode;
    private String raceCode;
    private String raceNum;
    private String raceSel;
    private String dateTime;
    private String dChgReq;
    private String notified;
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
