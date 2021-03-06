package mcssoft.com.raceremindermvp.model.impl;

import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.view.View;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.meeting.MeetingAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.MeetingLoader;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.impl.base.BaseModelImpl;
import mcssoft.com.raceremindermvp.network.DownloadRequest;
import mcssoft.com.raceremindermvp.network.DownloadRequestQueue;
import mcssoft.com.raceremindermvp.utility.MeetingList;
import mcssoft.com.raceremindermvp.utility.OpType;
import mcssoft.com.raceremindermvp.utility.Url;
import mcssoft.com.raceremindermvp.utility.Url2;

import static mcssoft.com.raceremindermvp.utility.OpType.MType.COUNT_MEETINGS;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.DELETE_MEETING;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.DELETE_MEETINGS;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.DOWNLOAD_MEETINGS;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.INSERT_MEETINGS;
import static mcssoft.com.raceremindermvp.utility.OpType.MType.SELECT_MEETINGS;

public class MeetingModelImpl extends BaseModelImpl {

    public MeetingModelImpl(@NonNull IPresenterModel iPresenterModel) {
        // retain reference to the IPresenterModel interface.
        this.iPresenterModel = iPresenterModel;
        Context context = iPresenterModel.getContext();
        // set the adapter.
        setAdapter();
        // set the database.
        raceDatabase = RaceDatabase.getInstance(context);
        // set the loader manager.
        loaderManager = iPresenterModel.getActivity().getLoaderManager();
        // resource bindings
        ButterKnife.bind(this, new View(context)); // a bit of a hack but seems to work.
        //
        doMeetingOps(COUNT_MEETINGS, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Volley">
    /**
     * Volley return point.
     * @param response The Volley response object.
     */
    @Override
    public void onResponse(Object response) {
        /* Note: the response object is actually a list of Meeting objects. */
        // cancel dialog.
        if(iPresenterModel.isProgressDialogShowing()) {
            iPresenterModel.showProgressDialog(false, null);
        }

        if(response == null) {
            // TODO - what if the response object is null for some reason.
        } else if(response != null && ((List) response).size() < 1) {
            // TODO - what if the response object is valid (not null) but contains no data.
        } else {
            // the response object contains data.
            switch(opType) {
                case DOWNLOAD_MEETINGS:
                    // set operation type flag.
                    doMeetingOps(INSERT_MEETINGS, response);
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
            Map<String,String> headers = networkResponse.headers;
            String bp = "";
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
    public void onLoadFinished(Loader loader, Object object) {
        switch(opType) {
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
            case DELETE_MEETINGS:
                onLoadFinishedDeleteMeetings();
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        meetingAdapter.swapData(null);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter.IMeeting">
    @Override
    public void deleteMeetings() {
        doMeetingOps(DELETE_MEETINGS, null);
    }

    @Override
    public void downloadMeetings() {
        doMeetingOps(DOWNLOAD_MEETINGS, null);
    }

    @Override
    public void clearMeetingDisplay() {
        meetingAdapter.swapData(null);
    }

    @Override
    public Meeting getMeeting(int lPos) {
        return meetingAdapter.getMeeting(lPos);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: onLoadFinished methods">
    /**
     * MeetingLoader.onLoadFinished and operation type was OpType.COUNT_MEETINGS.
     * @param object The count of meetings (as int).
     */
    private void onLoadFinishedCountMeetings(Object object) {
        if((int) object < 1) {
            // no Meeting records exist, so download them.
            if(getNetworkCheck()) {
                doMeetingOps(DOWNLOAD_MEETINGS, null);
            } else {
                // no meetings in database and no network.
                iPresenterModel.showNoNetworkDialog();
                meetingAdapter.swapData(null);
            }
        } else {
            // Meeting records already exist in database, select them so we can load up the
            // adapter.
            doMeetingOps(SELECT_MEETINGS, null);
        }
    }

    /**
     * MeetingLoader.onLoadFinished and operation type was INSERT_MEETINGS.
     */
    private void onLoadFinishedInsertMeetings() {
        if(iPresenterModel.isProgressDialogShowing()) {
            iPresenterModel.showProgressDialog(false, null);
        }
        // now select the Meeting records so we can load up the adapter.
        doMeetingOps(SELECT_MEETINGS, null);
    }

    private void onLoadFinishedDeleteMeetings() {
        //
        doMeetingOps(DELETE_MEETINGS, null);
    }

    /**
     * MeetingLoader.onLoadFinished and operation type was SELECT_MEETINGS.
     * @param object The selected Meeting records.
     */
    private void onLoadFinishedSelectMeetings(Object object) {
        if(iPresenterModel.isProgressDialogShowing()) {
            iPresenterModel.showProgressDialog(false, null);
        }
        // load up the adapter.
        meetingAdapter.swapData((List<Meeting>) object);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: doMeetingOps methods">
    /**
     * A switchboard foe Meeting operations.
     * @param opType The operation type (from enum OpTyp).
     * @param object A data object.
     */
    private void doMeetingOps(@OpType.MType int opType, @Nullable Object object) {
        this.opType = opType;
        switch(opType) {
            case COUNT_MEETINGS:
            case SELECT_MEETINGS:
            case DELETE_MEETINGS:
                Bundle bundle = new Bundle();
                bundle.putInt(bundle_key, opType);
                setLoaderManager(1, bundle);
                break;
            case DOWNLOAD_MEETINGS:
                doMeetingOpsDownloadMeetings();
                break;
            case INSERT_MEETINGS:
                doMeetingOpsInsertMeetings(opType, object);
                break;
        }
    }

    /**
     * Download today's Meetings.
     */
    private void doMeetingOpsDownloadMeetings() {
//        Url url = new Url(iPresenterModel.getContext());
        Url2 url2 = new Url2(iPresenterModel.getContext());
        String uri = url2.createRaceDayUrl(null);
        iPresenterModel.showProgressDialog(true, getting_meetings);
        DownloadRequest dlReq = new DownloadRequest(Request.Method.GET, uri, iPresenterModel.getContext(), this, this, table_meetings);
        DownloadRequestQueue.getInstance(iPresenterModel.getContext()).addToRequestQueue(dlReq);
    }

    /**
     * Insert the downloaded Meeing records.
     * @param opType The operation type for the MeetingLoader.
     * @param response The Meeting records.
     */
    private void doMeetingOpsInsertMeetings(@OpType.MType int opType, Object response) {
        // meetings have been downloaded, insert them into the database.
        if(!iPresenterModel.isProgressDialogShowing()) {
            iPresenterModel.showProgressDialog(true, writing_meetings);
        }
        
        Bundle bundle = new Bundle();
        bundle.putInt(bundle_key, opType);
        // get the list of Meeting objects and set in bundle.
        MeetingList meetingList = new MeetingList(response);
        bundle.putParcelableArrayList(bundle_data_key, meetingList.getMeetingList());
        // restart loader to pickup changes.
        setLoaderManager(1, bundle);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    @Override
    protected void setAdapter() {
        meetingAdapter = new MeetingAdapter(iPresenterModel.getContext());
        meetingAdapter.setClickListener(iPresenterModel.getClickListener());
        iPresenterModel.getRecyclerView().setAdapter(meetingAdapter);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Variables">
    @OpType.MType int opType;               // current operation type.
    private MeetingAdapter meetingAdapter;  // recyclerview adapter.

    // String bindings.
    @BindString(R.string.bundle_key) String bundle_key;
    @BindString(R.string.bundle_data_key) String bundle_data_key;
    @BindString(R.string.getting_meetings) String getting_meetings;
    @BindString(R.string.table_meetings) String table_meetings;
    @BindString(R.string.writing_meetings) String writing_meetings;
    //</editor-fold>
}
