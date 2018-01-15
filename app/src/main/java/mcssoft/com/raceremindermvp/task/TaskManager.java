package mcssoft.com.raceremindermvp.task;

import android.os.AsyncTask;

import java.util.List;

import mcssoft.com.raceremindermvp.database.DatabaseOperations;
import mcssoft.com.raceremindermvp.interfaces.IModelTask;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.impl.MainModelImpl;

public class TaskManager {

    public TaskManager(DatabaseOperations dbOper, IModelTask iModelTask) {
        this.dbOper = dbOper;
        this.iModelTask = iModelTask;
    }

    /**
     * Get Meeting information.
     * @param opType The operation type to be performed (which Meeting information to select).
     */
    public void getMeetings(MainModelImpl.OpType opType) {
        DatabaseAsyncTask databaseAsyncTask = new DatabaseAsyncTask(opType);
        databaseAsyncTask.execute();
    }

    /**
     * Set Meeting information.
     * @param opType The operation type to be performed (which Meeting information to select).
     * @param data The data associated with the Heeting.
     */
    public void setMeetings(MainModelImpl.OpType opType, Object data) {
        DatabaseAsyncTask databaseAsyncTask = new DatabaseAsyncTask(opType);
        databaseAsyncTask.execute(data);
    }

    /**
     * Simple inner class that allows RaceDatabase to run on background thread.
     */
    private class DatabaseAsyncTask extends AsyncTask<Object, Void, Object> {

        private MainModelImpl.OpType opType;

        /**
         * Perform action on the database in the background.
         * @param opType The type of operation to perform.
         */
        protected DatabaseAsyncTask(MainModelImpl.OpType opType) {
            this.opType = opType;
        }

        @Override
        protected Object doInBackground(Object... params) {
            Object data = new Object();
            switch(opType) {
                //  Request a count of Meeting records.
                case MEETINGS_COUNT:
                    data = dbOper.getTableRowCount("MEETINGS", null, null);
                break;
                // Request to select all Meetings.
                case MEETINGS_SELECTED:
                    data = dbOper.getFromTable("MEETINGS", null, null, null);
                    break;
                // Request to insert Meetings.
                case MEETINGS_INSERTED:
                    List<Meeting> lMeetings = (List<Meeting>) params[0];  // testing.
                    for (Meeting meeting : lMeetings) {
                        dbOper.insertMeetingRecord(meeting);
                    }
                    break;
            }
            return data;
        }

        @Override
        protected void onPostExecute(Object data) {
            iModelTask.onPostExecute(data, opType);
        }
    }

    private DatabaseOperations dbOper;
    private IModelTask iModelTask;
}
