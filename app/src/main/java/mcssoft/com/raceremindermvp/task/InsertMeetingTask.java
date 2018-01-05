package mcssoft.com.raceremindermvp.task;

import android.content.Context;

import mcssoft.com.raceremindermvp.database.RaceDatabase;

public class InsertMeetingTask extends BaseTask {

    public InsertMeetingTask(Context context, RaceDatabase raceDatabase, Object data) {
        super(context, raceDatabase, data);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // TBA
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        super.onProgressUpdate(values);
        // TBA
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return super.doInBackground(objects);
    }

    @Override
    protected void onPostExecute(Object object) {
        super.onPostExecute(object);
    }

    @Override
    public Object onTaskFinnish() {
        return super.onTaskFinnish();
    }
}
