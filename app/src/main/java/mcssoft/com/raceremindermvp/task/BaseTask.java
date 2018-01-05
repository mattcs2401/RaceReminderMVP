package mcssoft.com.raceremindermvp.task;

import android.content.Context;
import android.os.AsyncTask;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.IAsyncTask;

public class BaseTask extends AsyncTask implements IAsyncTask {

    public BaseTask(Context context, RaceDatabase raceDatabase, Object data) {
        //TBA
    }

    @Override
    protected void onPreExecute() {
        // Doco:  invoked on the UI thread before the task is executed. This step is normally used
        //        to setup the task, for instance by showing a progress bar in the user interface.
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Object[] values) {
        // Doco: invoked on the UI thread after a call to publishProgress(Progress...). The timing
        //       of the execution is undefined. This method is used to display any form of progress
        //       in the user interface while the background computation is still executing. For
        //       instance, it can be used to animate a progress bar or show logs in a text field.
        super.onProgressUpdate(values);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        // Doco:  invoked on the background thread immediately after onPreExecute() finishes
        //        executing. This step is used to perform background computation that can take a
        //        long time. The parameters of the asynchronous task are passed to this step. The
        //        result of the computation must be returned by this step and will be passed back to
        //        the last step. This step can also use publishProgress(Progress...) to publish one
        //        or more units of progress. These values are published on the UI thread, in the
        //        onProgressUpdate(Progress...) step.
        return null;
    }

    @Override
    protected void onPostExecute(Object object) {
        // Doco:  invoked on the UI thread after the background computation finishes. The result of
        //        the background computation is passed to this step as a parameter.
        super.onPostExecute(object);
        this.object = object;
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IAsyncTask">
    @Override
    public Object onTaskFinnish() {
        return object;
    }
    //</editorr-fold>

    private Object object;
//    private Context context;
//    private String tableName;
//    private RaceDatabase raceDatabase;
}
