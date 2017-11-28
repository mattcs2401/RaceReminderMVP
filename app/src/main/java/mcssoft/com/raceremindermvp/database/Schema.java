package mcssoft.com.raceremindermvp.database;

public class Schema {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "RACEREMINDER";

    public static final String RACE_DETAILS = "RACEDETAILS";

    // Table RACEDETAILS columns
    public static final String COLUMN_ROWID = "_id"; // Note: Has to be like this (upper case _ID ?).
    public static final String COLUMN_CITY_CODE = "CITY_CODE";
    public static final String COLUMN_RACE_CODE = "RACE_CODE";
    public static final String COLUMN_RACE_NUM = "RACE_NUM";
    public static final String COLUMN_RACE_SEL = "RACE_SEL";
    public static final String COLUMN_DATE_TIME = "DATE_TIME";
    // Generic field to indicate a display change is required.
    public static final String COLUMN_D_CHG_REQ = "D_CHG_REQ";
    // Generic field that indicates if a notification set for the record.
    public static final String COLUMN_NOTIFIED = "NOTIFIED";

    // Database table create.
    public static final String DATABASE_CREATE = "CREATE TABLE "
            + RACE_DETAILS   + " ("
            + COLUMN_ROWID     + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CITY_CODE + " TEXT NOT NULL, "
            + COLUMN_RACE_CODE + " TEXT NOT NULL, "
            + COLUMN_RACE_NUM  + " INTEGER NOT NULL, "
            + COLUMN_RACE_SEL  + " INTEGER NOT NULL, "
            + COLUMN_DATE_TIME + " INTEGER NOT NULL, "
            + COLUMN_D_CHG_REQ + " TEXT NOT NULL, "
            + COLUMN_NOTIFIED  + " TEXT NOT NULL)";

    public static final String SORT_ORDER = COLUMN_DATE_TIME + " ASC, " + COLUMN_RACE_SEL;

    // Where a display change is required.
    public final static String WHERE_FOR_DCHANGE = COLUMN_D_CHG_REQ + " = ? AND " +
            COLUMN_DATE_TIME + " < ?";

    // where for meetings to notify.
    public static final String WHERE_FOR_NOTIFY = COLUMN_NOTIFIED + "='N'";

    // where for which meetings to show.
    public static final String WHERE_FOR_SHOW = COLUMN_DATE_TIME + " > ?";
}
