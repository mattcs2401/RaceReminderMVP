package mcssoft.com.raceremindermvp.model.impl;


import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import java.util.List;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.race.RaceAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceLoader;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.impl.base.BaseModelImpl;
import mcssoft.com.raceremindermvp.network.DownloadRequest;
import mcssoft.com.raceremindermvp.network.DownloadRequestQueue;
import mcssoft.com.raceremindermvp.utility.OpType;
import mcssoft.com.raceremindermvp.utility.Url;

import static mcssoft.com.raceremindermvp.utility.OpType.RType.COUNT_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.DELETE_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.DOWNLOAD_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.INSERT_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.SELECT_RACES;

public class RaceModelImpl extends BaseModelImpl {

    public RaceModelImpl(@NonNull IPresenterModel iPresenterModel, @NonNull Bundle arguments) {
        // retain reference to the IPresenterModel interface.
        this.iPresenterModel = iPresenterModel;
        Context context = iPresenterModel.getContext();
        // set the adapter.
        setMeetingAdapter();
        // set the database.
        raceDatabase = RaceDatabase.getInstance(context);
        // set the loader manager.
        loaderManager = iPresenterModel.getActivity().getLoaderManager();
        // resource bindings
        ButterKnife.bind(this, new View(context)); // a bit of a hack but seems to work.

        // clear any races that exist in the database
        doRaceOps(DELETE_RACES, null);
        // download races based on the meeting information
        doRaceOps(DOWNLOAD_RACES, arguments);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Volley">
    @Override
    public void onErrorResponse(VolleyError error) {
        String bp = "";
    }

    @Override
    public void onResponse(Object response) {
        /* Note: the response object is actually a list of Race objects. */
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
                    String bp = "";
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
                // Race information has been inserted into the database, now select them so we
                // can load up the adapter.
                onLoadFinishedInsertRaces();
                break;
//            case SELECT_MEETING:
//                onLoadFinishedSelectMeeting(object);
//                break;
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        raceAdapter.swapData(null);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: doLoadFinished methods">
    private void onLoadFinishedCountRaces(Object object) { }

    private void onLoadFinishedDownloadRaces() { }

    private void onLoadFinishedInsertRaces() { }

    private void onLoadFinishedSelectRaces(Object object) { }

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
                doRaceOpsInsertRaces(object);
                break;
        }
    }

    /**
     *
     * @param object The selected Meeting record.
     */
    private void doRaceOpsDownloadRaces(Object object) {
        // Note: meeting date is YYYY-MM-DD
        Meeting meeting = (Meeting) object;
        String meetingCode = meeting.getMeetingCode();
        Url url = new Url(iPresenterModel.getContext());
        String uri = url.createMeetingUrl(meeting.getMeetingDate(), meetingCode);
        iPresenterModel.showProgressDialog(true, getting_races);
        DownloadRequest dlReq = new DownloadRequest(Request.Method.GET, uri, iPresenterModel.getContext(), this, this, table_races);
        DownloadRequestQueue.getInstance(iPresenterModel.getContext()).addToRequestQueue(dlReq);
    }

    private void doRaceOpsInsertRaces(Object data) {
        String bp = "";

    }

    private void doRaceOpsOther() {
        Bundle bundle = new Bundle();
        bundle.putInt(bundle_key, opType);
        doLoaderManager(bundle);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Utility">
    /**
     * Initialise or restart the MeetingLoader.
     * @param bundle Data package.
     */
    @Override
    protected void doLoaderManager(Bundle bundle) {
        if(loaderManager.getLoader(2) != null) {
            // restart the loader to pick up new changes.
            loaderManager.restartLoader(2, bundle, this);
        } else {
            loaderManager.initLoader(2, bundle, this);
        }
    }

    private void setMeetingAdapter() {
        raceAdapter = new RaceAdapter();
        raceAdapter.setClickListener(iPresenterModel.getClickListener());
        iPresenterModel.getRecyclerView().setAdapter(raceAdapter);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Variables">
    @OpType.RType int opType;          // current operation type.
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
