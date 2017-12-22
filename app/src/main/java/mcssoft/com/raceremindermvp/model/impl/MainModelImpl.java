package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.arch.persistence.room.Room;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import mcssoft.com.raceremindermvp.adapter.MeetingAdapter;
import mcssoft.com.raceremindermvp.adapter.RaceAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceLoader;

public class MainModelImpl implements IModelPresenter, LoaderManager.LoaderCallbacks<List> {

    public MainModelImpl(IPresenterModel iPresenterModel) {
        // Retain reference to the IPresenterModel interface.
        this.iPresenterModel = iPresenterModel;
        // get reference to the recyclerview within the View .
        recyclerView = iPresenterModel.getRecyclerView();
        // set adapter.
        setMeetingAdapter();
        // create database instance.
        raceDatabase = Room.databaseBuilder(iPresenterModel.getContext(), RaceDatabase.class, "RaceDatabase.db").build();
        // initialise the Loader.
        iPresenterModel.getActivity().getLoaderManager().initLoader(0, null, this);
    }

    /**
     * Get the IModelPresenter interface.
     * @return The IModelPresenter interface.
     * Note: This is for the Presenter's access to the Model's methods exposed with the
     *       IModelPresenter interface.
     */
    public IModelPresenter getModel() {
        return iPresenterModel.getModel(this);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter">
    public boolean getNetworkCheck() {

        return true;
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
        recyclerView.setAdapter(meetingAdapter);
    }

    private RecyclerView recyclerView;           // access to the RecyclerView.
//    private RaceLoader raceLoader;           //
    private IPresenterModel iPresenterModel;     // access to IPresenterModel methods.
    private RaceDatabase raceDatabase;
    private MeetingAdapter meetingAdapter;
}
