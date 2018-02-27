package mcssoft.com.raceremindermvp.model.impl;


import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.race.RaceAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceLoader;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;
import mcssoft.com.raceremindermvp.model.impl.base.BaseModelImpl;
import mcssoft.com.raceremindermvp.network.DownloadRequest;
import mcssoft.com.raceremindermvp.network.DownloadRequestQueue;
import mcssoft.com.raceremindermvp.utility.OpType;
import mcssoft.com.raceremindermvp.utility.RaceList;
import mcssoft.com.raceremindermvp.utility.Url;

import static mcssoft.com.raceremindermvp.utility.OpType.RType.COUNT_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.DELETE_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.DOWNLOAD_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.INSERT_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.SELECT_RACES;

public class RaceModelImpl extends BaseModelImpl {

    public RaceModelImpl(@NonNull IPresenterModel iPresenterModel, @NonNull Bundle arguments) {
        this.arguments = arguments;
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

//        // clear any races that exist in the database
//        doRaceOps(DELETE_RACES, null);
//        // download races based on the meeting information
//        doRaceOps(DOWNLOAD_RACES, arguments);
        doRaceOps(COUNT_RACES, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Volley">
    @Override
    public void onErrorResponse(VolleyError error) {
        String bp = "";
    }

    /**
     * Volley non-error return point.
     * @param response A list of Race objects.
     */
    @Override
    public void onResponse(Object response) {
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
                case DOWNLOAD_RACES:
                    doRaceOps(INSERT_RACES, response);
                    break;

            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return new RaceLoader(iPresenterModel.getContext(), raceDatabase, args);
    }

    @Override
    public void onLoadFinished(Loader loader, Object object) {
        switch(opType) {
            case COUNT_RACES:
                onLoadFinishedCountRaces(object);
                break;
            case SELECT_RACES:
                // select on Race records returns here.
                onLoadFinishedSelectRaces(object);
                break;
            case DELETE_RACES:
                onLoadFinishedDeleteRaces();
                break;
            case DOWNLOAD_RACES:
                onLoadFinishedDownloadRaces();
                break;
            case INSERT_RACES:
                onLoadFinishedInsertRaces();
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        raceAdapter.swapData(null);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter.IRace">
    @Override
    public void deleteRaces() {
        doRaceOps(DELETE_RACES, null);
    }

    @Override
    public void downloadRaces() {
        doRaceOps(DOWNLOAD_RACES, null);
    }

    @Override
    public void clearRaceDisplay() {
        raceAdapter.swapData(null);
    }

    @Override
    public Race getRace(int lPos) {
        return raceAdapter.getRace(lPos);
    }

    @Override
    public List<String> getMeetingInfo() {
        List<String> lMeetingInfo = new ArrayList<String>();
        Meeting meeting = (Meeting) arguments.getParcelable(bundle_key);
        lMeetingInfo.add(0, meeting.getMeetingCode());
        lMeetingInfo.add(1, meeting.getVenueName());
        lMeetingInfo.add(2, meeting.getTrackDesc() + " " + meeting.getTrackRating());
        lMeetingInfo.add(3, meeting.getWeatherDesc());
        return lMeetingInfo;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: doLoadFinished methods">
    private void onLoadFinishedCountRaces(Object object) {
        if((int) object < 2) {
            // no Race records exist, so download them.
            if(getNetworkCheck()) {
                doRaceOps(DOWNLOAD_RACES, arguments);
            } else {
                // no race in database and no network.
                iPresenterModel.showNoNetworkDialog();
                raceAdapter.swapData(null);
            }
        } else {
            // Race records already exist in database, select them so we can load up the
            // adapter.
            doRaceOps(SELECT_RACES, null);
        }
    }

    private void onLoadFinishedDownloadRaces() {

    }

    private void onLoadFinishedInsertRaces() {
        if(iPresenterModel.isProgressDialogShowing()) {
            iPresenterModel.showProgressDialog(false, null);
        }
        // now select the Meeting records so we can load up the adapter.
        doRaceOps(SELECT_RACES, null);
    }

    private void onLoadFinishedSelectRaces(Object object) {
        if(iPresenterModel.isProgressDialogShowing()) {
            iPresenterModel.showProgressDialog(false, null);
        }
        // load up the adapter.
        raceAdapter.swapData((List<Race>) object);
    }

    private void onLoadFinishedDeleteRaces() { }

    private void onLoadFinishedSelectMeeting(Object object) {
        String bp = "";
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: doRaceOps methods">
    /**
     * A switchboard foe Meeting operations.
     * @param opType The operation type (from enum OpTyp).
     * @param object A data object.
     */
    private void doRaceOps(@OpType.RType int opType, @Nullable Object object) {
        // set the current operation type.
        this.opType = opType;

        switch(opType) {
            case COUNT_RACES:
            case SELECT_RACES:
            case DELETE_RACES:
                doRaceOpsOther();
                break;
            case DOWNLOAD_RACES:
                doRaceOpsDownloadRaces(object);
                break;
            case INSERT_RACES:
                doRaceOpsInsertRaces(INSERT_RACES, object);
                break;
        }
    }

    /**
     *
     * @param object The selected Meeting record.
     */
    private void doRaceOpsDownloadRaces(Object object) {
        Meeting meeting = ((Bundle) object).getParcelable(bundle_key);
        Url url = new Url(iPresenterModel.getContext());
        String uri = url.createMeetingUrl(meeting.getMeetingDate(), meeting.getMeetingCode());
        iPresenterModel.showProgressDialog(true, getting_races);
        DownloadRequest dlReq = new DownloadRequest(Request.Method.GET, uri, iPresenterModel.getContext(), this, this, table_races);
        DownloadRequestQueue.getInstance(iPresenterModel.getContext()).addToRequestQueue(dlReq);
    }

    private void doRaceOpsInsertRaces(@OpType.RType int opType, Object data) {
        // meetings have been downloaded, insert them into the database.
        if(!iPresenterModel.isProgressDialogShowing()) {
            iPresenterModel.showProgressDialog(true, writing_races);
        }

        Bundle bundle = new Bundle();
        bundle.putInt(bundle_key, opType);
        // get the list of Meeting objects and set in bundle.
        RaceList raceList = new RaceList(data);
        bundle.putParcelableArrayList(bundle_data_key, raceList.getRaceList());
        // restart loader to pickup changes.
        setLoaderManager(2, bundle);
    }

    private void doRaceOpsOther() {
        Bundle bundle = new Bundle();
        bundle.putInt(bundle_key, opType);
        setLoaderManager(2, bundle);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    @Override
    protected void setAdapter() {
        raceAdapter = new RaceAdapter(iPresenterModel.getContext());
        raceAdapter.setClickListener(iPresenterModel.getClickListener());
        iPresenterModel.getRecyclerView().setAdapter(raceAdapter);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Variables">
    @OpType.RType int opType;          // current operation type.
    private Bundle arguments;
    private RaceAdapter raceAdapter;   // recyclerview adapter.

    // String bindings.
    @BindString(R.string.bundle_key) String bundle_key;
    @BindString(R.string.bundle_data_key) String bundle_data_key;
    @BindString(R.string.getting_races) String getting_races;
    @BindString(R.string.writing_races) String writing_races;
    @BindString(R.string.table_races) String table_races;
    @BindString(R.string.xml_extn) String xml_extn;
    //</editor-fold>
}
