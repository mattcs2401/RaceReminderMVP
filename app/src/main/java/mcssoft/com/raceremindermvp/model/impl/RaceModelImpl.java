package mcssoft.com.raceremindermvp.model.impl;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import butterknife.BindString;
import butterknife.ButterKnife;
import mcssoft.com.raceremindermvp.R;
import mcssoft.com.raceremindermvp.adapter.RaceAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.utility.OpType;

import static mcssoft.com.raceremindermvp.utility.OpType.RType.COUNT_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.DOWNLOAD_RACES;
import static mcssoft.com.raceremindermvp.utility.OpType.RType.INSERT_RACES;
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

        doRaceOps(COUNT_RACES, null);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(Loader loader) {

    }
    //</editor-fold>

    /**
     * A switchboard foe Meeting operations.
     * @param opType The operation type (from enum OpTyp).
     * @param object A data object.
     */
    private void doRaceOps(@OpType.RType int opType, @Nullable Object object) {
        this.opType = opType;
        switch(opType) {
            case COUNT_RACES:
                doRaceOpsCountRaces(opType);
                break;
            case DOWNLOAD_RACES:
                doRaceOpsDownloadRaces();
                break;
            case INSERT_RACES:
                doRaceOpsInsertRaces(opType, object);
                break;
            case SELECT_RACES:
                doRaceOpsSelectRaces(opType);
                break;
        }
    }

    private void doRaceOpsCountRaces(@OpType.RType int opType) {
        String bp = "";
    }

    private void doRaceOpsDownloadRaces() {
        String bp = "";

    }

    private void doRaceOpsInsertRaces(@OpType.RType int opType, Object data) {
        String bp = "";

    }

    private void doRaceOpsSelectRaces(@OpType.RType int opType) {
        String bp = "";

    }

    private void setMeetingAdapter() {
        raceAdapter = new RaceAdapter();
        raceAdapter.setClickListener(iPresenterModel.getClickListener());
        iPresenterModel.getRecyclerView().setAdapter(raceAdapter);
    }

    private RaceAdapter raceAdapter;       // recyclerview adapter.

    @OpType.RType int opType;     // current operation type.

    // String bindings.
    @BindString(R.string.bundle_key) String bundle_key;
    @BindString(R.string.bundle_data_key) String bundle_data_key;
    @BindString(R.string.getting_races) String getting_races;
    @BindString(R.string.writing_races) String writing_races;
}
