package mcssoft.com.raceremindermvp.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.IModelTask;
import mcssoft.com.raceremindermvp.model.impl.MainModelImpl;

public class TaskManager {

    public TaskManager(Context context, RaceDatabase raceDatabase, IModelTask iModelTask, Bundle args) {
        this.context = context;
        this.raceDatabase = raceDatabase;
        this.args = args;
        this.iModelTask = iModelTask;
    }

    public void getMeetings(MainModelImpl.OpType opType) {
        SelectDbAsync selectDbAsync = new SelectDbAsync(opType);
        selectDbAsync.execute();
    }

    private class SelectDbAsync extends AsyncTask<Void, Void, Object> {
        public SelectDbAsync(MainModelImpl.OpType opType) {
//            super();
            this.opType = opType;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
            }
            return data;

        }

        @Override
        protected void onPostExecute(Object data) {
            //super.onPostExecute(data);
            iModelTask.onPostExecute(data);
        }

        private MainModelImpl.OpType opType;
    }


    private Context context;
    private RaceDatabase raceDatabase;
    private Bundle args;
    private IModelTask iModelTask;
}
