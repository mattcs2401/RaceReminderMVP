package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import butterknife.BindString;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.MeetingAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.MeetingLoader;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.network.DownloadRequest;
import mcssoft.com.raceremindermvp.network.DownloadRequestQueue;
import mcssoft.com.raceremindermvp.utility.MeetingList;
import mcssoft.com.raceremindermvp.utility.Url;

public class MainModelImpl
    implements Response.Listener, Response.ErrorListener, LoaderManager.LoaderCallbacks<Object>, IModelPresenter  {

    public MainModelImpl(IPresenterModel iPresenterModel) {
        // retain reference to the IPresenterModel interface.
        this.iPresenterModel = iPresenterModel;
        // set the adapter.
        setMeetingAdapter();
        // set the database.
        raceDatabase = Room.databaseBuilder(iPresenterModel.getContext(), RaceDatabase.class, "RACEREMINDER").build();
        // set the loader manager.
        loaderManager = iPresenterModel.getActivity().getLoaderManager();

        // testing
        doMeetingOps(OpType.COUNT_MEETINGS, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter">
//    @Override
//    public Object getMeetings() {
//
////        raceLoaderManager.initLoader(raceDatabase, null);
//        return null;
//    }

//    @Override
//    public Object getRaces() {
//        return null;
//    }

    /**
     * Get the Races for a Meeting.
     * @return A count of the Races.
     */
//    @Override
//    public void downloadRaces() {
//        // TBA
//    }
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
            // the response object contains data.
            switch(opType) {
                case DOWNLOAD_MEETINGS:
                    // cancel previous progress.
                    iPresenterModel.showProgressDialog(false, null);
                    // meetings have been downloaded, insert them into the database.
                    iPresenterModel.showProgressDialog(true, writing_meetings);
                    // set operation type flag.
                    doMeetingOps(OpType.INSERT_MEETINGS, response);
                    break;
            }
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

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">
    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new MeetingLoader(iPresenterModel.getContext(), raceDatabase, bundle);
    }

    /**
     * AsyncTaskLoader.deliverResult returns here.
     * @param loader The Loader used.
     * @param object The result object.
     */
    @Override
    public void onLoadFinished(Loader<Object> loader, Object object) {
        switch(opType) {
            // a count of Meeting records was requested. Param 'object' will be an int.
            case COUNT_MEETINGS:
                onLoadFinishedCountMeetings(object);
                break;
            case INSERT_MEETINGS:
                // Meeting information has been inserted into the database, now select them so we
                // can load up the adapter.
                onLoadFinishedInsertMeetings();
                break;
            case SELECT_MEETINGS:
                // select on Meeting records returns here.
                onLoadFinishedSelectMeetings(object);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Object> loader) {
        meetingAdapter.swapData(null);
    }
    //</editor-fold>

    public enum OpType {
        DOWNLOAD_MEETINGS, COUNT_MEETINGS, SELECT_MEETINGS, INSERT_MEETINGS, MEETINGS_DATE_SELECT
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    /**
     * MeetingLoader.onLoadFinished and operation type was OpType.COUNT_MEETINGS.
     * @param object The count of meetings (as int).
     */
    private void onLoadFinishedCountMeetings(Object object) {
        if((int) object < 1) {
            // no Meeting records exist, so download them.
            if(getNetworkCheck()) {
                doMeetingOps(OpType.DOWNLOAD_MEETINGS, null);
            } else {
                // no meetings in database and no network.
                iPresenterModel.showNoNetworkDialog();
                meetingAdapter.swapData(null);
            }
        } else {
            // Meeting records already exist in database, select them so we can load up the
            // adapter.
            doMeetingOps(OpType.SELECT_MEETINGS, null);
        }
    }

    /**
     * MeetingLoader.onLoadFinished and operation type was OpType.INSERT_MEETINGS.
     */
    private void onLoadFinishedInsertMeetings() {
        // now select the Meeting  records so we can load up the adapter.
        doMeetingOps(OpType.SELECT_MEETINGS, null);
    }

    /**
     * MeetingLoader.onLoadFinished and operation type was OpType.SELECT_MEETINGS.
     * @param object The selected Meeting records.
     */
    private void onLoadFinishedSelectMeetings(Object object) {
        iPresenterModel.showProgressDialog(false, null);
        meetingAdapter.swapData((List<Meeting>) object);
    }

    /**
     * A switchboard foe Meeting operations.
     * @param opType The operation type (from enum OpTyp).
     * @param object A data object.
     */
    private void doMeetingOps(OpType opType, @Nullable Object object) {
        this.opType = opType;
        switch(opType) {
            case COUNT_MEETINGS:
                doMeetingOpsCountMeetings(opType);
                break;
            case DOWNLOAD_MEETINGS:
                doMeetingOpsDownloadMeetings();
                break;
            case INSERT_MEETINGS:
                doMeetingOpsInsertMeetings(opType, object);
                break;
            case SELECT_MEETINGS:
                doMeetingOpsSelectMeetings(opType);
                break;
        }
    }

    private void doMeetingOpsCountMeetings(OpType opType) {
        Bundle bundle = new Bundle();
        // set the current operation type in the bundle.
        bundle.putString(bundle_key, opType.toString());
        doLoaderManager(bundle);
    }

    /**
     * Download today's Meetings.
     */
    private void doMeetingOpsDownloadMeetings() {
        Url url = new Url(iPresenterModel.getContext());
        String uri = url.createRaceDayUrl(null);
        iPresenterModel.showProgressDialog(true, getting_meetings);
        DownloadRequest dlReq = new DownloadRequest(Request.Method.GET, uri, iPresenterModel.getContext(), this, this, "MEETINGS");
        DownloadRequestQueue.getInstance(iPresenterModel.getContext()).addToRequestQueue(dlReq);
    }

    private void doMeetingOpsInsertMeetings(OpType opType, Object response) {
        Bundle bundle = new Bundle();
        bundle.putString(bundle_key, opType.toString());
        // get the list of Meeting objects and set in bundle.
        MeetingList meetingList = new MeetingList(response);
        bundle.putParcelableArrayList(bundle_data_key, meetingList.getMeetingList());
        // restart loader to pickup changes.
        doLoaderManager(bundle);
    }

    private void doMeetingOpsSelectMeetings(OpType opType) {
        Bundle bundle = new Bundle();
        bundle.putString(bundle_key, opType.toString());
        doLoaderManager(bundle);
    }

    private void doLoaderManager(Bundle bundle) {
        if(loaderManager.getLoader(1) != null) {
            loaderManager.restartLoader(1, bundle, this);
        } else {
            loaderManager.initLoader(1, bundle, this);
        }
    }

    /**
     * Check that a connection exists.
     * @return True if a connection exists.
     */
    private boolean getNetworkCheck() {
        boolean networkExists = true;
        ConnectivityManager connMgr = (ConnectivityManager) iPresenterModel.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null || (!networkInfo.isAvailable() && !networkInfo.isConnected())) {
            networkExists = false;
        }
        return networkExists;
    }

    private void setMeetingAdapter() {
        meetingAdapter = new MeetingAdapter();
        meetingAdapter.setClickListener(iPresenterModel.getClickListener());
        iPresenterModel.getRecyclerView().setAdapter(meetingAdapter);
    }
    //</editor-fold>

    private LoaderManager loaderManager;
    private RaceDatabase raceDatabase;
    private MeetingAdapter meetingAdapter;
    private IPresenterModel iPresenterModel;     // access to IPresenterModel methods.
    private MainModelImpl.OpType opType;         // the current operation type;

    @BindString(R.string.bundle_key) String bundle_key;
    @BindString(R.string.bundle_data_key) String bundle_data_key;
    @BindString(R.string.getting_meetings) String getting_meetings;
    @BindString(R.string.writing_meetings) String writing_meetings;

}
