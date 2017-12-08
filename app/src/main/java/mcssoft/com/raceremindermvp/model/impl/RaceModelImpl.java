package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import java.util.List;

import mcssoft.com.raceremindermvp.interfaces.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.IPresenterModel;
import mcssoft.com.raceremindermvp.loader.RaceTaskLoader;
import mcssoft.com.raceremindermvp.model.Race;
import mcssoft.com.raceremindermvp.model.RaceList;

public class RaceModelImpl implements IModelPresenter { //}, LoaderManager.LoaderCallbacks<List<Race>> {

    public RaceModelImpl(IPresenterModel iPresenterModel) {
        this.iPresenterModel = iPresenterModel;
        raceList = new RaceList();
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
    public Race getRace(int id) {
        return null;
    }

    @Override
    public List<Race> getRaces(String whereCondition) {
        return null;
    }

    @Override
    public List<Race> getRaces() {
        raceLoader = new RaceTaskLoader(iPresenterModel.getContext());
        raceLoader.loadInBackground();
        return raceList.getRaces();
    }
    //</editor-fold>


    //<editor-fold defaultstate="collapsed" desc="Region: Loader callbacks">
//    @Override
//    public Loader<List<Race>> onCreateLoader(int id, Bundle args) {
//        return new RaceTaskLoader(iPresenterModel.getContext());
//    }
//
//    @Override
//    public void onLoadFinished(Loader<List<Race>> loader, List<Race> data) {
//        if(data != null && data.size() > 0) {
//            raceList.setRaces(data);
//        }
//    }
//
//    @Override
//    public void onLoaderReset(Loader<List<Race>> loader) {
//        // TBA - recyclerview adapter reset ?
//    }
    //</editor-fold>

    private RaceList raceList;         //
    private RaceTaskLoader raceLoader;     // access to database.
    private IPresenterModel iPresenterModel; // access to Presenter.
}
