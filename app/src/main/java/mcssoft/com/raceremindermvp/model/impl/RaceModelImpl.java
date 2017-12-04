package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import java.util.List;

import mcssoft.com.raceremindermvp.interfaces.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceLoader;
import mcssoft.com.raceremindermvp.model.Race;
import mcssoft.com.raceremindermvp.model.RaceList;

public class RaceModelImpl implements IModelPresenter, LoaderManager.LoaderCallbacks<List<Race>> {

    public RaceModelImpl(IPresenterModel model) {
        this.model = model;
        context = model.getContext();
        raceList = new RaceList();
    }

    //<editor-fold defaultstate="collapsed" desc="Region: Loader callbacks">
    @Override
    public Loader<List<Race>> onCreateLoader(int id, Bundle args) {
        raceLoader = new RaceLoader(context);
        return raceLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Race>> loader, List<Race> data) {
        if(data != null && data.size() > 0) {
            raceList.setRaces(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Race>> loader) {
        // TBA - recyclerview adapter reset ?
    }
    //</editor-fold>

    private Context context;       // context for database operations.
    private RaceList raceList;     //
    private RaceLoader raceLoader; // access to database.
    private IPresenterModel model; // access to Presenter.
}
