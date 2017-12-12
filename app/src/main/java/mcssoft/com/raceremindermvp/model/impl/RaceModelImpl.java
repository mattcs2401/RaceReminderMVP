package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.arch.persistence.room.Room;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceLoader;
import mcssoft.com.raceremindermvp.database.Race;
import mcssoft.com.raceremindermvp.model.RaceList;

public class RaceModelImpl implements IModelPresenter, LoaderManager.LoaderCallbacks {

    public RaceModelImpl(IPresenterModel iPresenterModel) {
        this.iPresenterModel = iPresenterModel;
//        raceList = new RaceList();
        recyclerView = iPresenterModel.getRecyclerView();
        raceDatabase = Room.databaseBuilder(iPresenterModel.getContext(), RaceDatabase.class, "RaceDatabase.db").build();
//        raceLoader = new RaceLoader(iPresenterModel.getContext(), raceDatabase);
//        raceLoader.loadInBackground();
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
    @Override
    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    @Override
    public Race getRace(int id) {
        return null;
    }

    @Override
    public List<Race> getRaces(String whereCondition) {
        return null;
    }

    @Override
    public List<Race> getRaces() {
//        raceList = new RaceList();
//        raceLoader.loadInBackground();
//        return raceList.getRaces();
        return null;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new RaceLoader(iPresenterModel.getContext(), raceDatabase);
    }

    @Override
    public void onLoadFinished(Loader loader, Object o) {
        String bp = "";
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

    //</editor-fold>
    private RaceList raceList;
    private RecyclerView recyclerView;           // access to the RecyclerView.
    private RaceLoader raceLoader;           //
    private IPresenterModel iPresenterModel;     // access to IPresenterModel methods.
    private RaceDatabase raceDatabase;
}
