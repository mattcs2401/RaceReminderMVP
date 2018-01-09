package mcssoft.com.raceremindermvp.task;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import mcssoft.com.raceremindermvp.database.RaceDatabase;

public class TaskManager {

    public TaskManager(Context context, RaceDatabase raceDatabase, Bundle args) {
        this.context = context;
        this.raceDatabase = raceDatabase;
        this.args = args;
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
            return raceDatabase.getMeetingDAO().selectAll("N");
        }

        @Override
        protected void onPostExecute(Object data) {
            super.onPostExecute(data);
        }
    }

    private Context context;
    private RaceDatabase raceDatabase;
    private Bundle args;
}
