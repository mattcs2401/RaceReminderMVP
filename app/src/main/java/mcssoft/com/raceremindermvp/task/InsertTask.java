package mcssoft.com.raceremindermvp.task;

import android.content.Context;
import android.os.AsyncTask;

import mcssoft.com.raceremindermvp.database.RaceDatabase;

public class InsertTask extends AsyncTask {

    /**
     *
     * @param context
     * @param raceDatabase
     * @param data
     * @param tableName
     */
    public InsertTask(Context context, RaceDatabase raceDatabase, Object data, String tableName) {

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    private Object object;
    private Context context;
    private String tableName;
    private RaceDatabase raceDatabase;

}
