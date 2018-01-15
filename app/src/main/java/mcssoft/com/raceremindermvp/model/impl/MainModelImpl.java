package mcssoft.com.raceremindermvp.model.impl;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import mcssoft.com.raceremindermvp.adapter.MeetingAdapter;
import mcssoft.com.raceremindermvp.database.DatabaseOperations;
import mcssoft.com.raceremindermvp.interfaces.IModelTask;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.network.DownloadRequest;
import mcssoft.com.raceremindermvp.network.DownloadRequestQueue;
import mcssoft.com.raceremindermvp.task.TaskManager;
import mcssoft.com.raceremindermvp.utility.Url;

public class MainModelImpl
    implements IModelPresenter, Response.Listener, Response.ErrorListener, IModelTask {

    public MainModelImpl(IPresenterModel iPresenterModel) {
        // retain reference to the IPresenterModel interface.
        this.iPresenterModel = iPresenterModel;
        // set the adapter.
        setMeetingAdapter();
        dbOper = new DatabaseOperations(iPresenterModel.getContext());
        // set the database TaskManager
        taskManager = new TaskManager(dbOper, this);
        // get the Meeting records for display.
        taskManager.getMeetings(OpType.MEETINGS_COUNT);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter">
    /**
     * Check that a connection exists.
     * @return True if a connection exists.
     */
    @Override
    public boolean getNetworkCheck() {
        boolean networkExists = true;
        ConnectivityManager connMgr = (ConnectivityManager) iPresenterModel.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || (!networkInfo.isAvailable() && !networkInfo.isConnected())) {
            networkExists = false;
        }
        return networkExists;
    }

    @Override
    public Object getMeetings() {

//        raceLoaderManager.initLoader(raceDatabase, null);
        return null;
    }

    /**
     * Download today's Meetings.
     */
    @Override
    public void downloadMeetings() {
        Url url = new Url(iPresenterModel.getContext());
        String uri = url.createRaceDayUrl(null);
        iPresenterModel.showProgressDialog(true, "Getting Meetings.");
        DownloadRequest dlReq = new DownloadRequest(Request.Method.GET, uri, iPresenterModel.getContext(), this, this, "MEETINGS");
        DownloadRequestQueue.getInstance(iPresenterModel.getContext()).addToRequestQueue(dlReq);
    }

    @Override
    public Object getRaces() {
        return null;
    }

    /**
     * Get the Races for a Meeting.
     * @return A count of the Races.
     */
    @Override
    public void downloadRaces() {
        // TBA
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Volley">
    /**
     * Volley return point.
     * @param response The Volley response object.
     */
    @Override
    public void onResponse(Object response) {
        /* Note: the response object is actually a list of objects (either Meeting, Race etc). */
        iPresenterModel.showProgressDialog(false, null);
        if(response == null) {
            // TODO - what if the response object is null for some reason.
        } else if(response != null && ((List) response).size() < 1) {
            // TODO - what if the response object is valid (not null) but contains no data.
        } else {
            // response object contains data.
            switch(opType) {
                case MEETINGS_DOWNLOADED:
                    // cancel previous progress.
                    iPresenterModel.showProgressDialog(false, null);
                    // meetings have been downloaded so insert them into the database.
                    iPresenterModel.showProgressDialog(true, "Writing Meeting information ...");
                    taskManager.setMeetings(opType.MEETINGS_INSERTED, response);
                    break;
            }
            // TODO - what sort of response object data, Meeting, Race etc.

        }
    }

    /**
     * Volley error return point.
     * @param error The Volley error object.
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        iPresenterModel.showProgressDialog(false, null);
        NetworkResponse networkResponse = error.networkResponse;
        if(networkResponse == null) {
            if(!getNetworkCheck()) {
                iPresenterModel.showNoNetworkDialog();
            }
        } else {
            // Some sort of network error, e.g. 404 page not found etc.
//            Map<String,String> headers = networkResponse.headers;
            // TODO - some generic error dialog ?
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IModelTask"
    /**
     * The processing of the async task for RaceDatabase returns here.
     * @param result The data returned from a RaceDatabase query/insert/update.
     * @param opType The original operation type sent to the asynctask.
     */
    @Override
    public void onPostExecute(Object result, OpType opType) {
        this.opType = opType;
        switch(opType) {
            // Meetings have been (downloaded and) inserted into the database.
            case MEETINGS_INSERTED:
                iPresenterModel.showProgressDialog(false, null);
                // select the Meetings to show.
                iPresenterModel.showProgressDialog(true, "Getting Meeting information ...");
                this.opType = OpType.MEETINGS_SELECTED;
                taskManager.getMeetings(OpType.MEETINGS_SELECTED);
                break;
            // A count of the Meetings was requested.
            case MEETINGS_COUNT:
                // check Meetings exist.
                if((int) result < 1) {
                    // no Meetings exist in the database, so download with Volley.
                    if(getNetworkCheck()) {
                        this.opType = OpType.MEETINGS_DOWNLOADED;
                        // this will return in the Volley response.
                        downloadMeetings();
                    } else {
                        // TBA - network check failed.
                        String bp = "";
                    }
                } else {
                    // Meetings exist in the database.
                    this.opType = OpType.MEETINGS_SELECTED;
                    taskManager.getMeetings(OpType.MEETINGS_SELECTED);
                }
                break;
            // Meetings have been selected from the database.
            case MEETINGS_SELECTED:
                iPresenterModel.showProgressDialog(false, null);
                String bp = "";
                // TBA
                break;
        }
    }
    //</editor-fold>

    public enum OpType {
        MEETINGS_DOWNLOADED, MEETINGS_COUNT, MEETINGS_SELECTED, MEETINGS_INSERTED
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void setMeetingAdapter() {
        meetingAdapter = new MeetingAdapter();
        meetingAdapter.setClickListener(iPresenterModel.getClickListener());
        iPresenterModel.getRecyclerView().setAdapter(meetingAdapter);
    }
    //</editor-fold>

    private TaskManager taskManager;
    private DatabaseOperations dbOper;
    private MeetingAdapter meetingAdapter;
    private IPresenterModel iPresenterModel;     // access to IPresenterModel methods.
    private MainModelImpl.OpType opType;

}
