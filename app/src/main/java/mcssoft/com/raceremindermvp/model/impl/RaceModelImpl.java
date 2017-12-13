package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.arch.persistence.room.Room;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import mcssoft.com.raceremindermvp.adapter.RaceAdapter;
import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceLoader;

public class RaceModelImpl implements IModelPresenter, LoaderManager.LoaderCallbacks<List> {

    public RaceModelImpl(IPresenterModel iPresenterModel) {
        // Retain reference to the IPresenterModel interface.
        this.iPresenterModel = iPresenterModel;
        // get reference to the recyclerview within the View .
        recyclerView = iPresenterModel.getRecyclerView();
        // set adapter.
        setRaceAdapter();
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
    // TBA
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">
    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new RaceLoader(iPresenterModel.getContext(), raceDatabase);
    }

    @Override
    public void onLoadFinished(Loader<List> loader, List list) {
        raceAdapter.swapData(list);
        if(list != null && list.size() > 0) {
            raceAdapter.setEmptyView(false);
        } else {
            raceAdapter.setEmptyView(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List> loader) {
        raceAdapter.swapData(null);
    }
    //</editor-fold>

    private void setRaceAdapter() {
        raceAdapter = new RaceAdapter();
        raceAdapter.setClickListener(iPresenterModel.getClickListener());
        recyclerView.setAdapter(raceAdapter);
    }

    private RecyclerView recyclerView;           // access to the RecyclerView.
    private RaceLoader raceLoader;           //
    private IPresenterModel iPresenterModel;     // access to IPresenterModel methods.
    private RaceDatabase raceDatabase;
    private RaceAdapter raceAdapter;
}
