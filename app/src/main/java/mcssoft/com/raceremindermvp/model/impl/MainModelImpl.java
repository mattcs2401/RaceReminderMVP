package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import mcssoft.com.raceremindermvp.adapter.MeetingAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceLoader;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.network.DownloadRequest;
import mcssoft.com.raceremindermvp.network.DownloadRequestQueue;
import mcssoft.com.raceremindermvp.utility.Url;

public class MainModelImpl
    implements IModelPresenter,
               Response.Listener,
               Response.ErrorListener,
               LoaderManager.LoaderCallbacks<List> {

    public MainModelImpl(IPresenterModel iPresenterModel) {
        // Retain reference to the IPresenterModel interface.
        this.iPresenterModel = iPresenterModel;
        // set adapter.
        setMeetingAdapter();
        // create database instance.
        raceDatabase = Room.databaseBuilder(iPresenterModel.getContext(), RaceDatabase.class, "RaceDatabase.db").build();
        // initialise the Loader.
//        iPresenterModel.getActivity().getLoaderManager().initLoader(0, null, this);
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

    /**
     * Get today's Meetings.
     */
    @Override
    public void getMeetings() {
        Url url = new Url(iPresenterModel.getContext());
        String uri = url.createRaceDayUrl(null);
        iPresenterModel.showProgressDialog(true);
        DownloadRequest dlReq = new DownloadRequest(Request.Method.GET, uri, iPresenterModel.getContext(), this, this, "MEETINGS");
        DownloadRequestQueue.getInstance(iPresenterModel.getContext()).addToRequestQueue(dlReq);
    }

    /**
     * Get the Races for a Meeting.
     * @return A count of the Races.
     */
    @Override
    public void getRaces() {
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
        // Note: the response object is actually a list of objects (Meeting, Race etc).
        iPresenterModel.showProgressDialog(false);
        databaseCheckAndLoad(response);
    }

    /**
     * Volley error return point.
     * @param error The Volley error object.
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        iPresenterModel.showProgressDialog(false);
        NetworkResponse networkResponse = error.networkResponse;
        if(networkResponse == null) {
            if(!getNetworkCheck()) {
                iPresenterModel.showNoNetworkDialog();
            }
        } else {
            // Some sort of network error, e.g. 404 page not found etc.
//            Map<String,String> headers = networkResponse.headers;
//            this.bundle.clear();
//            this.bundle.putString("meetings_show_empty_key", null);
//            loadMeetingsFragment(this.bundle);
            // TODO - some generic error dialog ?
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">
    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new RaceLoader(iPresenterModel.getContext(), raceDatabase);
    }

    @Override
    public void onLoadFinished(Loader<List> loader, List list) {
        meetingAdapter.swapData(list);
        if(list != null && list.size() > 0) {
            meetingAdapter.setEmptyView(false);
        } else {
            meetingAdapter.setEmptyView(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List> loader) {
        meetingAdapter.swapData(null);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    private void setMeetingAdapter() {
        meetingAdapter = new MeetingAdapter();
        meetingAdapter.setClickListener(iPresenterModel.getClickListener());
        iPresenterModel.getRecyclerView().setAdapter(meetingAdapter);
    }

    /**
     * Parse the response from Volley into Meeting objects to be written to the database.
     * @param response The Volley response object.
     */
    private void databaseCheckAndLoad(Object response) {
        // cast into list so we can iterate.
        List<Meeting> meetings = (ArrayList) response;
        for(Meeting meeting : meetings) {
            raceDatabase.getMeetingDAO().insert(meeting);
        }
        String bp = "";
    }
    //</editor-fold>

    private RaceLoader raceLoader;               //
    private IPresenterModel iPresenterModel;     // access to IPresenterModel methods.
    private RaceDatabase raceDatabase;
    private MeetingAdapter meetingAdapter;


}
