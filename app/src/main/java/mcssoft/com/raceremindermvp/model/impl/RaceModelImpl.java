package mcssoft.com.raceremindermvp.model.impl;


import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.volley.Request;

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
import static mcssoft.com.raceremindermvp.utility.OpType.RType.SELECT_MEETING;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.SELECT_RACES;

public class RaceModelImpl extends BaseModelImpl {

    public RaceModelImpl(IPresenterModel iPresenterModel, Bundle arguments) {
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
        // get the meeting database row id from the arguments
        this.arguments = arguments;
        // download races based on the meeting information
        doRaceOps(SELECT_MEETING, null);
    }

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
                // select on Meeting records returns here.
                onLoadFinishedSelectRaces(object);
                break;
            case DELETE_RACES:
                onLoadFinishedDeleteRaces();
                break;
            case DOWNLOAD_RACES:
                onLoadFinishedDownloadRaces();
                break;
            case INSERT_RACES:
                // Meeting information has been inserted into the database, now select them so we
                // can load up the adapter.
                onLoadFinishedInsertRaces();
                break;
            case SELECT_MEETING:
                onLoadFinishedSelectMeeting(object);
                break;
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
        doRaceOpsDownloadRaces(object);
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: doRaceOps methods">
    /**
     * A switchboard foe Meeting operations.
     * @param opType The operation type (from enum OpTyp).
     * @param object A data object.
     */
    private void doRaceOps(@OpType.RType int opType, @Nullable Object object) {
        Bundle bundle = null;
        this.opType = opType;
        switch(opType) {
            case COUNT_RACES:
            case SELECT_RACES:
            case DELETE_RACES:
                bundle = new Bundle();
                bundle.putInt(bundle_key, opType);
                doLoaderManager(bundle);
                break;
            case DOWNLOAD_RACES:
                doRaceOpsDownloadRaces(object);
                break;
            case INSERT_RACES:
                doRaceOpsInsertRaces(opType, object);
                break;
            case SELECT_MEETING:
                bundle = new Bundle();
                bundle.putInt(bundle_key, opType);
                bundle.putInt(bundle_data_key, arguments.getInt(bundle_key));
                doLoaderManager(bundle);
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
        String meetingCode = meeting.getMeetingCode() + "xml";
        Url url = new Url(iPresenterModel.getContext());
        String uri = url.createMeetingUrl(new String[] {"meetingdate"}, meetingCode);
//        iPresenterModel.showProgressDialog(true, getting_races);
//        DownloadRequest dlReq = new DownloadRequest(Request.Method.GET, uri, iPresenterModel.getContext(), this, this, table_races);
//        DownloadRequestQueue.getInstance(iPresenterModel.getContext()).addToRequestQueue(dlReq);
    }

    private void doRaceOpsInsertRaces(@OpType.RType int opType, Object data) {
        String bp = "";

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

    private Bundle arguments;
    private RaceAdapter raceAdapter;       // recyclerview adapter.

    @OpType.RType int opType;     // current operation type.

    // String bindings.
    @BindString(R.string.bundle_key) String bundle_key;
    @BindString(R.string.bundle_data_key) String bundle_data_key;
    @BindString(R.string.getting_races) String getting_races;
    @BindString(R.string.writing_races) String writing_races;
    @BindString(R.string.table_races) String table_races;
}
