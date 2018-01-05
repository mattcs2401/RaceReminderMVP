package mcssoft.com.raceremindermvp.task;

import android.content.Context;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.IAsyncTask;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.utility.Resources;

public class TaskManager {

    public TaskManager(IPresenterModel iPresenterModel) {
        this.iPresenterModel = iPresenterModel;
        context = iPresenterModel.getContext();
    }

    public void insertMeeting(RaceDatabase raceDatabase, Object data) {
        iAsyncTask = new InsertMeetingTask(context, raceDatabase, data, Resources.getInstance(context).getString(R.string.meetings_tag));
    }

    public Object get() {
        // TBA - how do we check task status.
        return iAsyncTask.onTaskFinnish();
    }

    private Context context;
    private IAsyncTask iAsyncTask;
    private IPresenterModel iPresenterModel;
}
