package mcssoft.com.raceremindermvp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.util.Log;

import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;
import mcssoft.com.raceremindermvp.model.database.Runner;

/**
 * Utility class to perform database activities / actions.
 */
public class DatabaseOperations {

    public DatabaseOperations(Context context) {
        this.context = context;
        dbHelper = DatabaseHelper.getInstance(context);
    }

    /**
     * Get all the records in a table.
     *
     * @param tableName The table name.
     * @return A cursor over the records.
     * Note: No guarantee the cursor contains anything.
     */
    public Cursor getAllFromTable(String tableName) {
        SQLiteDatabase db = dbHelper.getDatabase();
        db.beginTransaction();
        Cursor cursor = db.query(tableName, getProjection(tableName), null, null, null, null, null);
        db.endTransaction();
        return cursor;
    }

    /**
     * Delete all the records in a table.
     *
     * @param tableName The table name.
     * @return The number of rows deleted.
     */
    public int deleteAllFromTable(String tableName) {
        int rows = 0;
        if (checkTableRowCount(tableName, null, null)) {
            SQLiteDatabase db = dbHelper.getDatabase();
            db.beginTransaction();
            rows = db.delete(tableName, "1", null);
            db.setTransactionSuccessful();
            db.endTransaction();
        }
        return rows;
    }

    /**
     * Utility wrapper method to query the database.
     *
     * @param tableName   The table name.
     * @param columnNames The table columns required (Null equals all columns).
     * @param whereClause Where clause (without the "where").
     * @param whereVals   Where clause values
     * @return A cursor over the parseResult set.
     */
    public Cursor getSelectionFromTable(String tableName, @Nullable String[] columnNames, String whereClause, String[] whereVals) {
        if (columnNames == null) {
            columnNames = getProjection(tableName);
        }
        SQLiteDatabase db = dbHelper.getDatabase();
        db.beginTransaction();
        Cursor cursor = db.query(tableName, columnNames, whereClause, whereVals, null, null, null);
        db.endTransaction();
        return cursor;
    }

    /**
     * Utility method to update a single value in a single row.
     *
     * @param tableName The table name.
     * @param where     The where clause (without the "where").
     * @param rowId     The table row id.
     * @param colName   The table column name.
     * @param value     The column value.
     * @return The update count.
     */
    public int updateTableByRowId(String tableName, String where, int rowId, String colName, String value) {
        SQLiteDatabase db = dbHelper.getDatabase();

        ContentValues cv = new ContentValues();
        cv.put(colName, value);

        db.beginTransaction();
        int counr = db.update(tableName, cv, where, new String[]{Integer.toString(rowId)});
        db.setTransactionSuccessful();
        db.endTransaction();

        return counr;
    }

    /**
     * Utility to create the '?' parameter part of an IN statement.
     *
     * @param iterations The number of '?' characters to insert.
     * @return The formatted string e.g. " IN (?,?)".
     */
    public String createWhereIN(int iterations) {
        StringBuilder sb = new StringBuilder();
        sb.append(" IN (");
        for (int ndx = 0; ndx < iterations; ndx++) {
            sb.append("?,");
        }
        sb.deleteCharAt(sb.length() - 1);   // remove last comma.
        sb.append(")");
        return sb.toString();
    }

    /**
     * Check a record exists using the given record identifier.
     *
     * @param tableName  The record's associated table.
     * @param columnName The record's column to check.
     * @param identifier The identifier in the column.
     * @return True if record exists.
     */
    public boolean checkRecordsExist(String tableName, String columnName, String identifier) {
        Cursor cursor = null;
        String[] col = new String[]{columnName};
        String[] id = new String[]{identifier};
        switch (tableName) {
            case DatabaseConstants.MEETINGS_TABLE:
                cursor = getSelectionFromTable(tableName, col, DatabaseConstants.WHERE_MEETING_ID, id);
                break;
            case DatabaseConstants.RACES_TABLE:
                cursor = getSelectionFromTable(tableName, col, DatabaseConstants.WHERE_RACE_MEETING_ID, id);
                break;
            case DatabaseConstants.RUNNERS_TABLE:
                // TODO - where clause for select on RUNNERS table.
                break;
        }
        return ((cursor != null) && (cursor.getCount() > 0));
    }

    /**
     * Insert a record into the MEETINGS table.
     *
     * @param meeting Meeting object to derive values from.
     */
    public void insertMeetingRecord(Meeting meeting) {
        SQLiteDatabase db = dbHelper.getDatabase();
        ContentValues cv = new ContentValues();

        // Note: derived from RaceDay.xml.
        cv.put(DatabaseConstants.MEETING_DATE, meeting.getMeetingDate());
        cv.put(DatabaseConstants.MEETING_ABANDONED, meeting.getAbandoned());
        cv.put(DatabaseConstants.MEETING_VENUE, meeting.getVenueName());
        cv.put(DatabaseConstants.MEETING_HI_RACE, meeting.getHiRaceNo());
        cv.put(DatabaseConstants.MEETING_CODE, meeting.getMeetingCode());
        cv.put(DatabaseConstants.MEETING_ID, meeting.getMeetingId());
//        cv.put(DatabaseConstants.MEETING_TRACK_DESC, meeting.getTrackDescription());
        cv.put(DatabaseConstants.MEETING_TRACK_RATING, meeting.getTrackRating());
//        cv.put(DatabaseConstants.MEETING_WEATHER_DESC, meeting.getTrackWeather());

        try {
            db.beginTransaction();
            db.insertOrThrow(DatabaseConstants.MEETINGS_TABLE, null, cv);
            db.setTransactionSuccessful();
        } catch (SQLException ex) {
            Log.d(context.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Insert a record into the RACES table.
     *
     * @param race Race object to derive values from.
     */
    public void insertRaceRecord(Race race) {
        SQLiteDatabase db = dbHelper.getDatabase();
        ContentValues cv = new ContentValues();

        cv.put(DatabaseConstants.RACE_MEETING_ID, race.getMeetingId());
        cv.put(DatabaseConstants.RACE_NO, race.getRaceNumber());
        cv.put(DatabaseConstants.RACE_TIME, race.getRaceTime());
        cv.put(DatabaseConstants.RACE_NAME, race.getRaceName());
        cv.put(DatabaseConstants.RACE_DIST, race.getRaceDistance());

        try {
            db.beginTransaction();
            db.insertOrThrow(DatabaseConstants.RACES_TABLE, null, cv);
            db.setTransactionSuccessful();
        } catch (SQLException ex) {
            Log.d(context.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Utility method to see if rows exist in the given table.
     * @param tableName The table to check.
     * @param whereClause Optional where clause.
     * @param whereArgs Optional arguments for where clause.
     * @return True if the row count > 0.
     */
    public boolean checkTableRowCount(String tableName, @Nullable String whereClause, @Nullable String[] whereArgs) {
        Cursor cursor = null;
        String rowIdName = getRowIdName(tableName);
        SQLiteDatabase db = dbHelper.getDatabase();
        db.beginTransaction();
        if ((whereClause == null && whereArgs == null) ||
            (whereClause != null && whereArgs == null) ||
            (whereClause == null && whereArgs != null)) {
            cursor = getSelectionFromTable(tableName, new String[]{rowIdName}, null, null);
        } else {
            cursor = getSelectionFromTable(tableName, new String[]{rowIdName}, whereClause, whereArgs);
        }
        db.endTransaction();
        return (cursor.getCount() > 0);
    }

    /**
     * Utility method to the cxount of rows in the given table.
     * @param tableName The table to check.
     * @param whereClause Optional where clause.
     * @param whereArgs Optional arguments for where clause.
     * @return The record count.
     */
    public int getTableRowCount(String tableName, @Nullable String whereClause, @Nullable String[] whereArgs) {
        Cursor cursor = null;
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = dbHelper.getDatabase();
            String rowIdName = getRowIdName(tableName);
            sqLiteDatabase.beginTransaction();
            if ((whereClause == null && whereArgs == null) ||
                (whereClause != null && whereArgs == null) ||
                (whereClause == null && whereArgs != null)) {
            cursor = getSelectionFromTable(tableName, new String[]{rowIdName}, null, null);
            } else {
                cursor = getSelectionFromTable(tableName, new String[]{rowIdName}, whereClause, whereArgs);
            }
        } catch (Exception ex) {
            Log.d(context.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            if(sqLiteDatabase != null) {
                sqLiteDatabase.endTransaction();
            }
            return cursor.getCount();
        }
    }

    /**
     * Utility method to check if rows exist in the Meetings table for the given date.
     * @param date The date to check (formatted YYYY-M(M)-D(D)).
     * @param code Optional race code value, else set as null.
     * @return True if records exist.
     */
    public boolean checkMeetingDate(String date, @Nullable String code) {
        String whereClause;
        String[] whereArgs;
        if(code == null) {
            whereClause = DatabaseConstants.WHERE_MEETING_DATE;
            whereArgs = new String[] {date};
        } else {
            whereClause = DatabaseConstants.WHERE_MEETING_DATE_CODE;
            whereArgs = new String[] {date, code};
        }
        SQLiteDatabase db = dbHelper.getDatabase();
        db.beginTransaction();
        Cursor cursor = getSelectionFromTable(DatabaseConstants.MEETINGS_TABLE,
                new String[] {DatabaseConstants.MEETING_ROWID}, whereClause, whereArgs);
        db.endTransaction();
        return (cursor.getCount() > 0);
    }

    /**
     * Insert a record into the RUNNERS table.
     * @param runner Runner object to derive values from.
     */
    public void insertRunnerRecord(Runner runner) {
        SQLiteDatabase db = dbHelper.getDatabase();
        ContentValues cv = new ContentValues();

        // TODO - ContentValues for insert of RUNNER record.

        try {
            db.beginTransaction();
            db.insertOrThrow(DatabaseConstants.RUNNERS_TABLE, null, cv);
            db.setTransactionSuccessful();
        } catch (SQLException ex) {
            Log.d(context.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            db.endTransaction();
        }
    }

    /**
     * Get the associated DatabseHelper object.
     * @return The DatabaseHelper.
     */
    public DatabaseHelper getDbHelper() {
        return dbHelper;
    }

    private String[] getProjection(String tableName) {
        String[] projection = {};
        switch (tableName) {
            case DatabaseConstants.MEETINGS_TABLE:
                projection = DatabaseProjection.getProjection(DatabaseHelper.Projection.MeetingSchema);
                break;
            case DatabaseConstants.RACES_TABLE:
                projection = DatabaseProjection.getProjection(DatabaseHelper.Projection.RaceSchema);
                break;
        }
        return  projection;
    }

    /**
     * Get the name of the applicable row id column.
     * @param tableName The table name.
     * @return The row id column name.
     */
    private String getRowIdName(String tableName) {
        String rowId = "";
        switch(tableName) {
            case DatabaseConstants.MEETINGS_TABLE:
                rowId = DatabaseConstants.MEETING_ROWID;
                break;
            case DatabaseConstants.RACES_TABLE:
                rowId = DatabaseConstants.RACE_ROWID;
                break;
            case DatabaseConstants.RUNNERS_TABLE:
                rowId = DatabaseConstants.RUNNER_ROWID;
                break;
        }
        return rowId;
    }

    private Context context;
    private DatabaseHelper dbHelper;
}
