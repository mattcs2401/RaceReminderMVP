package mcssoft.com.raceremindermvp.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.IModelTask;
import mcssoft.com.raceremindermvp.model.impl.MainModelImpl;

public class TaskManager {

    public TaskManager(RaceDatabase raceDatabase, IModelTask iModelTask) {
        this.raceDatabase = raceDatabase;
        this.iModelTask = iModelTask;
    }

    /**
     * Get Meeting information.
     * @param opType The operation type to be performed (which Meeting information to select).
     */
    public void getMeetings(MainModelImpl.OpType opType) {
        SelectDbAsync selectDbAsync = new SelectDbAsync(opType);
        selectDbAsync.execute();
    }

    /**
     * Simple inner class that allows RaceDatabase to run on background thread.
     */
    private class SelectDbAsync extends AsyncTask<Void, Void, Object> {

        private MainModelImpl.OpType opType;

        public SelectDbAsync(MainModelImpl.OpType opType) {
            this.opType = opType;
        }

        @Override
        protected Object doInBackground(Void... params) {
            Object data = new Object();
            switch(opType) {
                case SELECT_MEETING_COUNT:
                    data = raceDatabase.getMeetingDAO().getCount("N");
                break;
                case SELECT_MEETINGS:
                    data = raceDatabase.getMeetingDAO().selectMeetings("N");
                    break;
                case SELECT_MEETING:
                    // TBA
                    break;
            }
            return data;
        }

        @Override
        protected void onPostExecute(Object data) {
            iModelTask.onPostExecute(data, opType);
        }
    }

    private RaceDatabase raceDatabase;
    private IModelTask iModelTask;
}
