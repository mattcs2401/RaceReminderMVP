package mcssoft.com.raceremindermvp.model.impl;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;

/**
 * Utility base class for the implementation of the Model.
 */
public abstract class BaseModelImpl
        implements Response.Listener, Response.ErrorListener, LoaderManager.LoaderCallbacks, IModelPresenter {

    //<editor-fold defaultstate="collapsed" desc="Region: Volley">
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {

    }
    //</editor-fold>

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

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter">
//    @Override
//    public Object getMeetings() {
//
////        raceLoaderManager.initLoader(raceDatabase, null);
//        return null;
//    }

//    @Override
//    public Object getRaces() {
//        return null;
//    }

    /**
     * Get the Races for a Meeting.
     * @return A count of the Races.
     */
//    @Override
//    public void downloadRaces() {
//        // TBA
//    }
    //</editor-fold>

    protected RaceDatabase raceDatabase;
    protected LoaderManager loaderManager;
    protected IPresenterModel iPresenterModel;
}
