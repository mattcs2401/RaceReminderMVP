package mcssoft.com.raceremindermvp.model.impl.base;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import mcssoft.com.raceremindermvp.database.RaceDatabase;
import mcssoft.com.raceremindermvp.interfaces.mvp.IModelPresenter;
import mcssoft.com.raceremindermvp.interfaces.mvp.IPresenterModel;
import mcssoft.com.raceremindermvp.model.database.Meeting;
import mcssoft.com.raceremindermvp.model.database.Race;

/**
 * Utility base class for the implementation of the Model.
 */
public abstract class BaseModelImpl
        implements Response.Listener, Response.ErrorListener, LoaderManager.LoaderCallbacks, IModelPresenter.IMeeting, IModelPresenter.IRace {

    //<editor-fold defaultstate="collapsed" desc="Region: Volley">
    @Override
    public void onErrorResponse(VolleyError error) { }

    @Override
    public void onResponse(Object response) { }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: Loader">
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) { }

    @Override
    public void onLoaderReset(Loader loader) { }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter.IMeeting">
    @Override
    public void deleteMeetings() { }

    @Override
    public void downloadMeetings() { }

    @Override
    public void clearMeetingDisplay() { }

    @Override
    public Meeting getMeeting(int lPos) { return null; }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Region: IModelPresenter.IRace">
    @Override
    public void deleteRaces() { }

    @Override
    public void downloadRaces() { }

    @Override
    public void clearRaceDisplay() { }

    @Override
    public Race getRace(int lPos) { return null; }
    //</editor-fold>

    protected void doLoaderManager(Bundle bundle) { }

    protected RaceDatabase raceDatabase;
    protected LoaderManager loaderManager;
    protected IPresenterModel iPresenterModel;
}
