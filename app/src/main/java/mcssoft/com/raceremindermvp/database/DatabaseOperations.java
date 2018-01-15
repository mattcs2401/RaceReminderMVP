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

/**
 * Utility class to perform database activities / actions.
 */
public class DatabaseOperations {

    public DatabaseOperations(Context context) {
        this.context = context;
        dbHelper = DatabaseHelper.getInstance(context);
    }

    /**
     * Delete all the records in a table.
     *
     * @param tableName The table name.
     * @return The number of rows deleted (a value of 0 means no rows deleted).
     */
    public int deleteFromTable(String tableName, @Nullable String whereClause, @Nullable String[] whereVals) {
        int rows = 0;
        boolean noWhere = false;
        if(whereClause == null || whereVals == null) {
            whereClause = null;
            whereVals = null;
            noWhere = true;
        }
        if (getTableRowCount(tableName, null, null) > 0) {
            // Only if there's rows to delete in the first place.
            SQLiteDatabase sqLiteDatabase = null;
            try {
                sqLiteDatabase = dbHelper.getDatabase();
                sqLiteDatabase.beginTransaction();
                if(noWhere) {
                    // deelete all.
                    rows = sqLiteDatabase.delete(tableName, "1", null);
                } else {
                    // delete selection.
                    rows = sqLiteDatabase.delete(tableName, whereClause, whereVals);
                }
                sqLiteDatabase.setTransactionSuccessful();
            } catch (Exception ex) {
                Log.d(context.getClass().getCanonicalName(), ex.getMessage());
            } finally {
                if (sqLiteDatabase != null) {
                    sqLiteDatabase.endTransaction();
                }
            }
        }
        return rows;
    }

    /**
     * Get all the records from a table, dependent on the 'where' params..
     * @param tableName   The table name.
     * @param columnNames The table columns required (Null equals all columns).
     * @param whereClause Where clause, or Null.
     * @param whereVals   Where clause values, or Null;
     * @return A cursor of the records for the table.
     * Note: If either whereClaues of whereVals is null, then both are treated as null.
     */
    public Cursor getFromTable(String tableName, @Nullable String[] columnNames, @Nullable String whereClause, @Nullable String[] whereVals) {
        Cursor cursor = null;
        boolean noWhere = false;
        SQLiteDatabase sqLiteDatabase = null;
        try {
            if (columnNames == null) {
                columnNames = getProjection(tableName);
            }
            if(whereClause == null || whereVals == null) {
                // if one is null, then both are null.
                whereClause = null;
                whereVals = null;
                noWhere = true;
            }
            sqLiteDatabase = dbHelper.getDatabase();
            sqLiteDatabase.beginTransaction();
            if(noWhere) {
                cursor = sqLiteDatabase.query(tableName, columnNames, null, null, null, null, null);
            } else {
                cursor = sqLiteDatabase.query(tableName, columnNames, whereClause, whereVals, null, null, null);
            }
        } catch(Exception ex) {
            Log.d(context.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            if(sqLiteDatabase != null) {
                sqLiteDatabase.endTransaction();
            }
            return cursor;
        }
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
     * Insert a record into the MEETINGS table.
     *
     * @param meeting Meeting object to derive values from.
     */
    public void insertMeetingRecord(Meeting meeting) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = dbHelper.getDatabase();
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.insertOrThrow(DatabaseConstants.MEETINGS_TABLE, null, setMeetingContentValues(meeting));
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException ex) {
            Log.d(context.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            if(sqLiteDatabase != null) {
                sqLiteDatabase.endTransaction();
            }
        }
    }

    /**
     * Insert a record into the RACES table.
     *
     * @param race Race object to derive values from.
     */
    public void insertRaceRecord(Race race) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = dbHelper.getDatabase();
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.insertOrThrow(DatabaseConstants.RACES_TABLE, null, setRaceContentValues(race));
            sqLiteDatabase.setTransactionSuccessful();
        } catch (SQLException ex) {
            Log.d(context.getClass().getCanonicalName(), ex.getMessage());
        } finally {
            if(sqLiteDatabase != null) {
                sqLiteDatabase.endTransaction();
            }
        }
    }

    /**
     * Utility method to the cxount of rows in the given table.
     * @param tableName The table to check.
     * @param whereClause Optional where clause.
     * @param whereArgs Optional arguments for where clause.
     * @return The record count. Can be either all records, or a sub-set based on the 'where' params.
     * Note: If either of the 'where' params is Null, then both are treated as Null.
     */
    public int getTableRowCount(String tableName, @Nullable String whereClause, @Nullable String[] whereArgs) {
        Cursor cursor = null;
        boolean noWhere = false;
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = dbHelper.getDatabase();
            sqLiteDatabase.beginTransaction();
            if (whereClause == null || whereArgs == null) {
                whereClause = null;
                whereArgs = null;
                noWhere = true;
            }
            if(noWhere) {
                // If being used, both whereClause and whereArgs must contain someting.
                cursor = getFromTable(tableName, new String[]{"_id"}, null, null);
            } else {
                cursor = getFromTable(tableName, new String[]{"_id"}, whereClause, whereArgs);
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

    private ContentValues setMeetingContentValues(Meeting meeting) {
        ContentValues contentValues = new ContentValues();
            // Note: derived from RaceDay.xml.
            contentValues.put(DatabaseConstants.MEETING_DATE, meeting.getMeetingDate());
            contentValues.put(DatabaseConstants.MEETING_ABANDONED, meeting.getAbandoned());
            contentValues.put(DatabaseConstants.MEETING_VENUE, meeting.getVenueName());
            contentValues.put(DatabaseConstants.MEETING_HI_RACE, meeting.getHiRaceNo());
            contentValues.put(DatabaseConstants.MEETING_CODE, meeting.getMeetingCode());
            contentValues.put(DatabaseConstants.MEETING_ID, meeting.getMeetingId());
//        cv.put(DatabaseConstants.MEETING_TRACK_DESC, meeting.getTrackDescription());
            contentValues.put(DatabaseConstants.MEETING_TRACK_RATING, meeting.getTrackRating());
//        cv.put(DatabaseConstants.MEETING_WEATHER_DESC, meeting.getTrackWeather());

        return contentValues;
    }

    private ContentValues setRaceContentValues(Race race) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.RACE_MEETING_ID, race.getMeetingId());
        contentValues.put(DatabaseConstants.RACE_NO, race.getRaceNumber());
        contentValues.put(DatabaseConstants.RACE_TIME, race.getRaceTime());
        contentValues.put(DatabaseConstants.RACE_NAME, race.getRaceName());
        contentValues.put(DatabaseConstants.RACE_DIST, race.getRaceDistance());
        return contentValues;
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

    private Context context;
    private DatabaseHelper dbHelper;
}
