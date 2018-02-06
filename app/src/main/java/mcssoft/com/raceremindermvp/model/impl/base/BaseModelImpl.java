package mcssoft.com.raceremindermvp.model.impl.base;

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
    public void deleteMeetings() { }

    @Override
    public void clearDisplay() { }
    //</editor-fold>

    protected RaceDatabase raceDatabase;
    protected LoaderManager loaderManager;
    protected IPresenterModel iPresenterModel;
}
