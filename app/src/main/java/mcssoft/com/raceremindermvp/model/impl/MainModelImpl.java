package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.IntentFilter;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.List;

import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.MeetingAdapter;
import mcssoft.com.raceremindermvp.adapter.RaceAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.dialog.NetworkDialog;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceLoader;
import mcssoft.com.raceremindermvp.network.DownloadRequestQueue;
import mcssoft.com.raceremindermvp.network.NetworkReceiver;
import mcssoft.com.raceremindermvp.utility.Resources;

public class MainModelImpl implements IModelPresenter, LoaderManager.LoaderCallbacks<List>,Response.Listener,
        Response.ErrorListener {

    public MainModelImpl(IPresenterModel iPresenterModel) {
        // Retain reference to the IPresenterModel interface.
        this.iPresenterModel = iPresenterModel;
        // set adapter.
        setMeetingAdapter();
        // create database instance.
        raceDatabase = Room.databaseBuilder(iPresenterModel.getContext(), RaceDatabase.class, "RaceDatabase.db").build();

        //DownloadRequestQueue.getInstance(iPresenterModel.getContext());

        // initialise the Loader.
        //iPresenterModel.getActivity().getLoaderManager().initLoader(0, null, this);

    }

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter">
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
    public int getMeetings() {
        return 0;
    }

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Volley">
    @Override
    public void onErrorResponse(VolleyError error) {
        ProgressDialog progressDialog = iPresenterModel.getProgressDialog();
        if(progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        NetworkResponse networkResponse = error.networkResponse;
        if(networkResponse == null) {
            if(!getNetworkCheck()) {
                iPresenterModel.getNetworkDialog();
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

    @Override
    public void onResponse(Object response) {

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

    private void setMeetingAdapter() {
        meetingAdapter = new MeetingAdapter();
        meetingAdapter.setClickListener(iPresenterModel.getClickListener());
        iPresenterModel.getRecyclerView().setAdapter(meetingAdapter);
    }

    private Bundle setEmptyView() {
        Bundle args = new Bundle();
        args.putString("meetings_show_empty_key", null);
        return args;
    }

    private RaceLoader raceLoader;               //
    private IPresenterModel iPresenterModel;     // access to IPresenterModel methods.
    private RaceDatabase raceDatabase;
    private MeetingAdapter meetingAdapter;
//    private NetworkReceiver receiver;       // for network availability check.
//    private ProgressDialog progressDialog;  // used by Volley download to show something happening.


}
