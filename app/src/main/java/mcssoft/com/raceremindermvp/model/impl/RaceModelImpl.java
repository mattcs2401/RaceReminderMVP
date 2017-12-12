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
import mcssoft.com.raceremindermvp.model.Race;

public class RaceModelImpl implements IModelPresenter, LoaderManager.LoaderCallbacks<Cursor> {

    public RaceModelImpl(IPresenterModel iPresenterModel) {
        this.iPresenterModel = iPresenterModel;

        recyclerView = iPresenterModel.getRecyclerView();
        raceDatabase = Room.databaseBuilder(iPresenterModel.getContext(), RaceDatabase.class, "RaceDatabase.db").build();

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

    private void setRaceAdapter() {
        raceAdapter = new RaceAdapter();
        raceAdapter.setOnItemClickListener(iPresenterModel.getClickListener());
        recyclerView.setAdapter(raceAdapter);
    }

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter">
    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">
    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new RaceLoader(iPresenterModel.getContext(), raceDatabase);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        String bp = "";
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        raceAdapter.swapCursor(null);
    }
    //</editor-fold>

    private RecyclerView recyclerView;           // access to the RecyclerView.
    private RaceLoader raceLoader;           //
    private IPresenterModel iPresenterModel;     // access to IPresenterModel methods.
    private RaceDatabase raceDatabase;
    private RaceAdapter raceAdapter;
}
