package mcssoft.com.raceremindermvp.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.IModelTask;

public class TaskManager {

    public TaskManager(Context context, RaceDatabase raceDatabase, IModelTask iModelTask, Bundle args) {
        this.context = context;
        this.raceDatabase = raceDatabase;
        this.args = args;
        this.iModelTask = iModelTask;
    }

    public Object getMeetings() {
        SelectDbAsync selectDbAsync = new SelectDbAsync();
        selectDbAsync.execute("");
        // testing
        return null;
    }

    private class SelectDbAsync extends AsyncTask<String, Void, Object> {
        public SelectDbAsync() {
            super();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(String... strings) {
            return raceDatabase.getMeetingDAO().getCount("N");//selectAll("N");
        }

        @Override
        protected void onPostExecute(Object data) {
            //super.onPostExecute(data);
            iModelTask.onPostExecute(data);
        }
    }


    private Context context;
    private RaceDatabase raceDatabase;
    private Bundle args;
    private IModelTask iModelTask;
}